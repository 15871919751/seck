package org.ct.seckill.service;

import org.ct.seckill.domain.OrderInfo;
import org.ct.seckill.domain.SecKillUser;
import org.ct.seckill.vo.GoodsVo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;


/**
 * @Author K
 * @Date 2020/1/26 22:05
 * @Version 1.0
 */
public interface MiaoshaService {
    /**
     *  返回OrderInfo订单
     * @param goodsVo 商品信息
     * @param user 用户信息
     * @return 返回OrderInfo对象
     * */
    OrderInfo secKillOrder(SecKillUser user, GoodsVo goodsVo);

    /**
     * 通过用户Id 商品Id 查询订单根据返回结果进行相应处理
     * @param goodsId 商品Id
     * @param id 用户Id
     * @return 返回处理结果 -1 秒杀失败 0处理中  orderId成功
     * */
    long getMiaoshaResult(Long id, Long goodsId);

    /**
     * 创建秒杀的接口地址
     * @param user 用户信息
     * @param goodsId 商品Id
     * @return 返回生成的秒杀地址
     * */
    String createMiaoshaPath(SecKillUser user,Long goodsId);

    /**
     * 根据参数到redis查找对应的秒杀接口地址并和path进行校验如果正确返回true 否则 false
     * @param user 用户信息
     * @param goodsId 商品Id
     * @param path 秒杀接口地址
     * @return 返回校验是否成功
     * */
    boolean getMiaoshaPath(String path, SecKillUser user, Long goodsId);

    /**
     * 生成验证码图片
     * @param goodsId 商品Id
     * @param response 请求对象
     * @param request  响应对象
     * @param user 用户信息
     * */
    void createVerifyCodeImg(SecKillUser user,long goodsId, HttpServletRequest request, HttpServletResponse response);
}
