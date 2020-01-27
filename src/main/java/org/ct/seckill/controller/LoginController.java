package org.ct.seckill.controller;

import lombok.extern.slf4j.Slf4j;

import org.ct.seckill.service.SeckillUserService;

import org.ct.seckill.vo.LoginVo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;


/**
 * @Author K
 * @Date 2020/1/18 0:26
 * @Version 1.0
 */

@Slf4j
@Controller
@RequestMapping(path = "/login")
public class LoginController {


    @Autowired
    SeckillUserService seckillUserService;


    @GetMapping(path = "/to_login")
    public String toLogin() {
        return "login";
    }

    @PostMapping(path = "/do_login")
    @ResponseBody
    public Object doLogin(HttpServletResponse response, @Valid LoginVo loginVo) {
       return seckillUserService.login(response, loginVo);
    }
}
