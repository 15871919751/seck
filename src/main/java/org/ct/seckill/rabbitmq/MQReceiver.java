package org.ct.seckill.rabbitmq;

import lombok.extern.slf4j.Slf4j;
import org.ct.seckill.domain.MiaoshaOrder;
import org.ct.seckill.domain.SecKillUser;
import org.ct.seckill.redis.RedisService;
import org.ct.seckill.service.GoosService;
import org.ct.seckill.service.MiaoshaService;
import org.ct.seckill.service.OrderService;
import org.ct.seckill.vo.GoodsVo;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author K
 * @Date 2020/2/3 13:15
 * @Version 1.0
 * rabbitMq消息接收
 */
@Slf4j
@Service
public class MQReceiver {

    @Autowired
    GoosService goodsService;

    @Autowired
    OrderService orderService;

    @Autowired
    MiaoshaService miaoshaService;

    @RabbitListener(queues = MQConfig.SEC_KILL_MESSAGE_QUEUE)
    public void receiver(String message) {
        log.info("receiver message:" + message);
        SeckillMessage seckillMessage = RedisService.stringToBean(message, SeckillMessage.class);
        long goodsId = seckillMessage.getGoodsId();
        SecKillUser user = seckillMessage.getUser();
        //判断库存
        GoodsVo goodsVo = goodsService.getGoodsVoByGoodsId(goodsId);
        if (goodsVo.getStockCount() <= 0) {
            return ;
        }
        //判断用户是否重复秒杀
        MiaoshaOrder miaoshaOrder =orderService.getSecKillOrderByUserIdGoodsId(user.getId(),goodsId);
        if (miaoshaOrder != null) {
            return ;
        } else {
        //减库存 下订单 写入秒杀订单
        miaoshaService.secKillOrder(user, goodsVo);
        }
    }

//    @RabbitListener(queues = MQConfig.TOPIC_QUEUE1)
//    public void receiverTopic1(String message) {
//        log.info("receiver fanout1 message:" + message);
//    }
//    @RabbitListener(queues = MQConfig.TOPIC_QUEUE2)
//    public void receiverTopic2(String message) {
//        log.info("receiver fanout2 message:" + message);
//    }
//    @RabbitListener(queues = MQConfig.HEADER_QUEUE)
//    public void receiverHeader(byte[] message) {
//        log.info("receiver header message:" + new String(message));
//    }


}
