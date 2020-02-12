package org.ct.seckill.service.impl;

import org.ct.seckill.dao.MiaoShaDao;
import org.ct.seckill.domain.MiaoshaOrder;
import org.ct.seckill.domain.OrderInfo;
import org.ct.seckill.domain.SecKillUser;
import org.ct.seckill.redis.MiaoshaKey;
import org.ct.seckill.redis.RedisService;
import org.ct.seckill.service.GoosService;
import org.ct.seckill.service.MiaoshaService;
import org.ct.seckill.service.OrderService;
import org.ct.seckill.util.MD5Util;
import org.ct.seckill.util.UUIDUtil;
import org.ct.seckill.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.security.provider.MD5;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

/**
 * @Author K
 * @Date 2020/1/26 22:05
 * @Version 1.0
 */
@Service("miaoshaService")
public class MiaoshaServiceImpl implements MiaoshaService {

    Random random = new Random();

    private int width = 120;

    private int height = 32;

    private int fontSize = 18;

    private String str = "+-*";

    private int value = 0;

    @Autowired
    MiaoShaDao miaoShaDao;

    @Autowired
    GoosService goosService;

    @Autowired
    OrderService orderService;

    @Autowired
    RedisService redisService;




    @Transactional(rollbackFor = Exception.class)
    @Override
    public OrderInfo secKillOrder(SecKillUser user, GoodsVo goodsVo) {
        //减库存
        boolean success = goosService.reduceStock(goodsVo);
        if (success) {
            //下订单
            return orderService.createOrder(user, goodsVo);
        } else {
            setGoodsOver(goodsVo.getId());
            return null;
        }
    }

    private void setGoodsOver(long id) {
        redisService.set(MiaoshaKey.GOODS_EMPTY, String.valueOf(id), true);
    }


    @Override
    public long getMiaoshaResult(Long id, Long goodsId) {
        MiaoshaOrder miaoshaOrder = orderService.getSecKillOrderByUserIdGoodsId(id, goodsId);
        if (miaoshaOrder != null) {
            //秒杀成功
            return miaoshaOrder.getId();
        } else {
            boolean result = getGoodsOver(goodsId);
            if (result) {
                return -1;
            } else {
                return 0;
            }
        }
    }

    @Override
    public String createMiaoshaPath(SecKillUser user,Long goodsId) {
        if (user == null || goodsId <= 0) {
            return null;
        }
        String path = MD5Util.md5(UUIDUtil.uuid());
        redisService.set(MiaoshaKey.MIAOSHA_PATH, user.getId() + "_" + goodsId, path);
        return path;
    }

    @Override
    public boolean getMiaoshaPath(String path, SecKillUser user, Long goodsId) {
        String oldPath = redisService.get(MiaoshaKey.MIAOSHA_PATH, user.getId() + "_" + goodsId, String.class);
        if (user != null && path.equals(oldPath)) {
            return true;
        }
        return false;
    }

    private boolean getGoodsOver(long goodsId) {
        return redisService.exists(MiaoshaKey.GOODS_EMPTY, String.valueOf(goodsId));
    }

    @Override
    public void createVerifyCodeImg(SecKillUser user,long goodsId, HttpServletRequest request, HttpServletResponse response) {
        if (user == null || goodsId <= 0) {
            return;
        }
        //设置画布
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        //获得画笔
        Graphics2D graphics2D = (Graphics2D) bufferedImage.getGraphics();
        //随机产生字符串
        String str = randomCode();
        redisService.set(MiaoshaKey.VERIFYCODE_KEY, user.getId() + "," + goodsId, value);
        //设置绘制区域
        graphics2D.fillRect(0, 0, width, height);
        //设置字体
        graphics2D.setFont(new Font("微软雅黑", Font.BOLD, fontSize));
        //设置验证码颜色
        for (int i = 0; i < str.length(); i++) {
            graphics2D.setColor(randomColor());
            graphics2D.drawString(String.valueOf(str.charAt(i)),8+i*fontSize, (fontSize + height)/2);
        }
        //绘制噪音线
        for (int i = 0; i < 2; i++) {
            graphics2D.setColor(randomColor());
            graphics2D.setStroke(new BasicStroke(2));
            graphics2D.drawLine(random.nextInt(width /2), random.nextInt(height / 2), random.nextInt(width), random.nextInt(height));
        }
        //作为图片并发送
        ServletOutputStream servletOutputStream = null;
        try {
            servletOutputStream = response.getOutputStream();
            ImageIO.write(bufferedImage,"png" ,servletOutputStream);
            servletOutputStream.flush();
            servletOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }



    }

    private Color randomColor() {
            int r = random.nextInt(256);
            int g = random.nextInt(256);
            int b = random.nextInt(256);
            return new Color(r, g, b);
    }

    private String randomCode() {
            int one = random.nextInt(10);
            int two = random.nextInt(10);
            char op = str.charAt(random.nextInt(str.length()));
            switch (op) {
                case '+':
                    value = one + two;
                    break;
                case '-':
                    value= (one > two ? one - two : two - one);
                    break;
                case '*':
                    value = one * two;
                    break;
                default:
            }
        return one > two ? one + "" + op + "" + two + "=?" : two + "" + op + "" + one + "=?";
    }

}
