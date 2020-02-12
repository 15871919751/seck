package org.ct.seckill.service;

import org.ct.seckill.domain.MiaoshaOrder;
import org.ct.seckill.domain.OrderInfo;
import org.ct.seckill.domain.SecKillUser;
import org.ct.seckill.vo.GoodsVo;


/**
 * @Author K
 * @Date 2020/1/26 22:05
 * @Version 1.0
 */
public interface OrderService {
    /**
     * 通过userId和goodsId返回秒杀信息
     * @param goodsId 商品Id
     * @param userId 秒杀用户Id
     * @return 返回MiaoshaOrder对象
     */
    MiaoshaOrder getSecKillOrderByUserIdGoodsId(Long userId, Long goodsId);

    /**
     * 通过user和goodsVo返回秒杀订单
     * @param user 用户信息
     * @param goodsVo 秒杀商品信息
     * @return 返回OrderInfo对象
     */
    OrderInfo createOrder(SecKillUser user, GoodsVo goodsVo);

    /**
     * 通过orderId查询一个秒杀商品的订单详情
     * @param orderId 秒杀订单的Id
     * @return 返回一个秒杀商品的具体订单信息
     * */
    OrderInfo getOrderInfo(long orderId);
}
