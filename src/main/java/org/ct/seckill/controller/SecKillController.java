package org.ct.seckill.controller;

import lombok.extern.slf4j.Slf4j;
import org.ct.seckill.access.AccessLimit;
import org.ct.seckill.common.CodeMsg;
import org.ct.seckill.common.MyException;
import org.ct.seckill.common.MyRsp;
import org.ct.seckill.domain.MiaoshaOrder;
import org.ct.seckill.domain.SecKillUser;
import org.ct.seckill.rabbitmq.MQSender;
import org.ct.seckill.rabbitmq.SeckillMessage;
import org.ct.seckill.redis.GoodsKey;
import org.ct.seckill.redis.MiaoshaKey;
import org.ct.seckill.redis.RedisService;
import org.ct.seckill.service.GoosService;
import org.ct.seckill.service.MiaoshaService;
import org.ct.seckill.service.OrderService;
import org.ct.seckill.vo.GoodsVo;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @Author K
 * @Date 2020/1/18 0:26
 * @Version 1.0
 */

@Slf4j
@Controller
@RequestMapping(path = "/miaosha")
public class SecKillController implements InitializingBean {

    private Map<Long, Boolean> map = new HashMap<>(5);

    @Autowired
    GoosService goodsService;

    @Autowired
    OrderService orderService;

    @Autowired
    MiaoshaService miaoshaService;

    @Autowired
    RedisService redisService;

    @Autowired
    MQSender sender;
    /**
     * 系统初始化
     * 将商品库存预加载入redis中
     * */
    @Override
    public void afterPropertiesSet() throws Exception {
        List<GoodsVo> goodsVoList = goodsService.getListGoodsVo();
        if (goodsVoList != null) {
            for (GoodsVo goodsVo : goodsVoList) {
                //将商品库存存入redis
                redisService.set(GoodsKey.SEC_KILL_COUNT, String.valueOf(goodsVo.getId()), goodsVo.getStockCount());
                //Long 商品Id   Boolean 商品库存是否为空标记
                map.put(goodsVo.getId(), false);
            }
        }
    }

    @RequestMapping(value = "/{path}/do_miaosha")
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    public Object ifLogin(@PathVariable("path") String path,Long goodsId, SecKillUser user) {
        if (user == null) {
            return MyRsp.wrapper(new MyException(CodeMsg.SESSION_ERROR));
        }

        boolean check = miaoshaService.getMiaoshaPath(path, user, goodsId);
        if (!check) {
            return MyRsp.wrapper(new MyException(CodeMsg.REQUEST_ILLEGAL));
        }
        //内存标记,对redis进行优化
        boolean isOver = map.get(goodsId);
        if (isOver) {
            return MyRsp.wrapper(new MyException(CodeMsg.SECKILL_OVER));
        }
        //redis预减库存
        long stock = redisService.decr(GoodsKey.SEC_KILL_COUNT, String.valueOf(goodsId));
        if (stock < 0) {
            map.put(goodsId, true);
            return MyRsp.wrapper(new MyException(CodeMsg.SECKILL_OVER));
        }
        //判断用户是否重复秒杀
       MiaoshaOrder miaoshaOrder =orderService.getSecKillOrderByUserIdGoodsId(user.getId(),goodsId);
       if (miaoshaOrder != null) {
           return MyRsp.wrapper(new MyException(CodeMsg.SECKILL_REPEAT));
       }else{
       //入队
        SeckillMessage message = new SeckillMessage(user, goodsId);
        sender.sendSeckillMessage(message);
        return MyRsp.success(0);
       }
    }
    /**
     *orderId 成功
     * -1 秒杀失败
     * 0 排队中
     * */
    @RequestMapping(value = "/result")
    @ResponseBody
    public Object MiaoshaResult(Long goodsId, SecKillUser user) {
        if (user == null) {
            return MyRsp.wrapper(new MyException(CodeMsg.SESSION_ERROR));
        }
        long result = miaoshaService.getMiaoshaResult(user.getId(), goodsId);
        return MyRsp.success(result);
    }

    @AccessLimit(second = 5, count = 5, needLogin = true)
    @RequestMapping(value = "/path")
    @ResponseBody
    public Object MiaoshaPath(Long goodsId, SecKillUser user, String verifyCode) {
        if (user == null) {
            return MyRsp.wrapper(new MyException(CodeMsg.SESSION_ERROR));
        }
        int verifyValue = redisService.get(MiaoshaKey.VERIFYCODE_KEY, user.getId() + "," + goodsId, int.class);
        if (verifyCode.equals(String.valueOf(verifyValue))) {
            String miaoshaPathPath = miaoshaService.createMiaoshaPath(user, goodsId);
            return MyRsp.success(miaoshaPathPath);
        } else {
            return MyRsp.wrapper(new MyException(CodeMsg.VERIFY_ERROR));
        }

    }

    @RequestMapping(value = "/verifyCode")
    @ResponseBody
    public void verifyCodeImg(@RequestParam("goodsId") long goodsId,SecKillUser user, HttpServletRequest request, HttpServletResponse response) {
        miaoshaService.createVerifyCodeImg(user,goodsId, request, response);
    }

}
