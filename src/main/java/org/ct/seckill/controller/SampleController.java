package org.ct.seckill.controller;

import org.ct.seckill.domain.User;
import org.ct.seckill.redis.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/test")
public class SampleController {

    @Autowired
    private RedisService redisService;

    @RequestMapping(path = "/set")
    public void setRedis(){
        User user = new User();
        user.setId(1);
        user.setName("haha2222");
        redisService.set("keys",user);
    }
}
