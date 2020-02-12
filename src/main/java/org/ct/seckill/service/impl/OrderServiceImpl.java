package org.ct.seckill.service.impl;

import org.ct.seckill.common.CodeMsg;
import org.ct.seckill.common.MyException;
import org.ct.seckill.common.MyRsp;
import org.ct.seckill.dao.OrderDao;
import org.ct.seckill.domain.MiaoshaOrder;
import org.ct.seckill.domain.OrderInfo;
import org.ct.seckill.domain.SecKillUser;
import org.ct.seckill.redis.OrderKey;
import org.ct.seckill.redis.RedisService;
import org.ct.seckill.service.OrderService;
import org.ct.seckill.vo.GoodsVo;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @Author K
 * @Date 2020/1/26 22:05
 * @Version 1.0
 */
@Service("orderService")
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderDao orderDao;

    @Autowired
    RedisService redisService;

    @Override
    public MiaoshaOrder getSecKillOrderByUserIdGoodsId(Long userId, Long goodsId) {
        //取缓存
        return redisService.get(OrderKey.getMiaoshaOrderByUidGid, "" + userId + "_" + goodsId, MiaoshaOrder.class);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public OrderInfo createOrder(SecKillUser user, GoodsVo goodsVo) {
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setCreateDate(new Date());
        orderInfo.setDeliveryAddrId(0L);
        orderInfo.setGoodsCount(1);
        orderInfo.setGoodsId(goodsVo.getId());
        orderInfo.setGoodsName(goodsVo.getGoodsName());
        orderInfo.setGoodsPrice(goodsVo.getSecKillPrice());
        orderInfo.setOrderChannel(1);
        orderInfo.setStatus(0);
        orderInfo.setUserId(user.getId());
        orderDao.insert(orderInfo);
        MiaoshaOrder miaoshaOrder = new MiaoshaOrder();
        miaoshaOrder.setGoodsId(goodsVo.getId());
        miaoshaOrder.setOrderId(orderInfo.getId());
        miaoshaOrder.setUserId(user.getId());
        orderDao.insertMiaoShaOrder(miaoshaOrder);
        redisService.set(OrderKey.getMiaoshaOrderByUidGid, "" + user.getId() + "_" + goodsVo.getId(), miaoshaOrder);
        return orderInfo;
    }

    @Override
    public OrderInfo getOrderInfo(long orderId) {
        return orderDao.selectOrderInfo(orderId);
    }


}
