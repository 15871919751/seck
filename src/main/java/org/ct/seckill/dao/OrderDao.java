package org.ct.seckill.dao;


import org.apache.ibatis.annotations.Param;
import org.ct.seckill.domain.MiaoshaOrder;
import org.ct.seckill.domain.OrderInfo;
import org.springframework.stereotype.Repository;

/**
 * @Author K
 * @Date 2020/1/28 21:48
 * @Version 1.0
 */
@Repository("orderDao")
public interface OrderDao {
    /**
     *  根据商品id和用户id来返回一个秒杀商品对象
     * @param goodsId 商品Id
     * @param userId 用户Id
     * @return 返回秒杀订单对象
     * */
    MiaoshaOrder getSecKillOrderByUserIdGoodsId(@Param("userId") Long userId, @Param("goodsId") Long goodsId);

    /**
     * 将生成的订单插入数据库
     * @param orderInfo 订单详细信息
     * @return 返回orderId
     * */
    long insert(OrderInfo orderInfo);

    /**
     * 将生成的秒杀订单插入数据库
     * @param miaoshaOrder 秒杀订单对象
     * */
    void insertMiaoShaOrder(MiaoshaOrder miaoshaOrder);

    /**
     * 通过秒杀订单的Id查询具体的秒杀订单
     * @param orderId 秒杀订单的Id
     * @return 返回具体的秒杀订单
     * */
    OrderInfo selectOrderInfo(@Param("orderId") long orderId);
}
