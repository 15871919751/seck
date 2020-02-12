package org.ct.seckill.rabbitmq;

import lombok.extern.slf4j.Slf4j;
import org.ct.seckill.redis.RedisService;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author K
 * @Date 2020/2/3 13:14
 * @Version 1.0
 * rabbitMq消息发送
 */
@Slf4j
@Service
public class MQSender {

    @Autowired
    AmqpTemplate amqpTemplate;

    public void sendSeckillMessage(SeckillMessage message) {
        String msg = RedisService.beanToString(message);
        log.info("send message -> {}",msg);
        amqpTemplate.convertAndSend(MQConfig.SEC_KILL_MESSAGE_QUEUE, msg);
    }

//    public void send(Object message) {
//        String msg = RedisService.beanToString(message);
//        log.info("send message:" + msg);
//        amqpTemplate.convertAndSend(MQConfig.QUEUE, msg);
//    }
//
//    public void sendTopic(Object message) {
//        String msg = RedisService.beanToString(message);
//        log.info("send message:" + msg);
//        amqpTemplate.convertAndSend(MQConfig.TOPIC_EXCHANGE,"topic.key1",msg+"1");
//        amqpTemplate.convertAndSend(MQConfig.TOPIC_EXCHANGE,"topic.key2",msg+"2");
//    }
//
//    public void sendFanout(Object message) {
//        String msg = RedisService.beanToString(message);
//        log.info("send message:" + msg);
//        amqpTemplate.convertAndSend(MQConfig.FANOUT_EXCHANGE,"",msg+"1");
//        amqpTemplate.convertAndSend(MQConfig.FANOUT_EXCHANGE,"",msg+"2");
//    }
//
//    public void sendHeader(Object message) {
//        String msg = RedisService.beanToString(message);
//        log.info("send message:" + msg);
//        MessageProperties properties = new MessageProperties();
//        properties.setHeader("header1", "value1");
//        properties.setHeader("header2", "value2");
//        Message obj = new Message(msg.getBytes(), properties);
//        amqpTemplate.convertAndSend(MQConfig.HEADER_EXCHANGE, "", obj);
//    }


}
