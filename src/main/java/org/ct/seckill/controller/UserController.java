package org.ct.seckill.controller;

import lombok.extern.slf4j.Slf4j;
import org.ct.seckill.common.MyRsp;
import org.ct.seckill.domain.SecKillUser;
import org.ct.seckill.service.GoosService;
import org.ct.seckill.service.SeckillUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;



/**
 * @Author K
 * @Date 2020/1/18 0:26
 * @Version 1.0
 */

@Slf4j
@Controller
@RequestMapping(path = "/user")
public class UserController {

    @Autowired
    SeckillUserService seckillUserService;

    @Autowired
    GoosService goodsService;

    @GetMapping("/userInfo")
    @ResponseBody
    public MyRsp list(Model model, SecKillUser user) {
        model.addAttribute("user", user);
        return MyRsp.success(user);
    }
}
