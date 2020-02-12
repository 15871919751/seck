package org.ct.seckill.controller;

import lombok.extern.slf4j.Slf4j;
import org.ct.seckill.common.MyRsp;
import org.ct.seckill.domain.User;
import org.ct.seckill.rabbitmq.MQSender;
import org.ct.seckill.redis.RedisService;
import org.ct.seckill.redis.UserKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author K
 */
@Slf4j
@Controller
@RequestMapping(path = "/test")
public class SampleController {

    @Autowired
     RedisService redisService;

    @Autowired
    MQSender mqSender;

//    @GetMapping(path = "/mq")
//    @ResponseBody
//    public Object mq() {
//        mqSender.send("hello mq");
//        return MyRsp.success("mq测试成功");
//    }
//
//    @GetMapping(path = "/mq/topic")
//    @ResponseBody
//    public Object mqTopic() {
//        mqSender.sendTopic("hello topicMq");
//        return MyRsp.success("topicMq测试成功");
//    }
//
//    @GetMapping(path = "/mq/fanout")
//    @ResponseBody
//    public Object mqFanout() {
//        mqSender.sendFanout("hello fanoutMq");
//        return MyRsp.success("fanoutMq测试成功");
//    }
//
//    @GetMapping(path = "/mq/header")
//    @ResponseBody
//    public Object mqHeader() {
//        mqSender.sendHeader("hello headerMq");
//        return MyRsp.success("headerMq测试成功");
//    }


    @GetMapping(path = "/set")
    public void setRedis() {
        User user = User.builder().id(1).name("111").build();
        redisService.set(UserKey.GET_BY_ID,""+1 ,user);
    }

    @GetMapping(path = "/get")
    @ResponseBody
    public User getRedis() {
        return redisService.get(UserKey.GET_BY_ID,""+1, User.class);
    }
}
