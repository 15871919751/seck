package org.ct.seckill.controller;

import lombok.extern.slf4j.Slf4j;
import org.ct.seckill.domain.User;
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
    private RedisService redisService;

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
