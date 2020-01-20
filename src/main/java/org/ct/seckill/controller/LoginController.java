package org.ct.seckill.controller;

import lombok.extern.slf4j.Slf4j;
import org.ct.seckill.common.CodeMsg;
import org.ct.seckill.common.MyException;
import org.ct.seckill.common.MyRsp;
import org.ct.seckill.domain.SecKillUser;
import org.ct.seckill.service.SeckillUserService;
import org.ct.seckill.util.ValidatorUtil;
import org.ct.seckill.vo.LoginVo;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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
    public Object doLogin( @Valid LoginVo loginVo) {
        if (loginVo == null) {
           return MyRsp.wrapper(new MyException(CodeMsg.MOBILE_PASSWORD_ERROR));
        }
        String mobile = loginVo.getMobile();
        String password = loginVo.getPassword();
        log.info(loginVo.toString());
        //参数校验
        SecKillUser secKillUser = seckillUserService.getUser(Long.valueOf(mobile));
        log.info("get mobile -> {}", mobile);
        log.info("secKillUser -> {}", secKillUser.toString());
        if (mobile.equals(secKillUser.getId()+"")&&password.equals(secKillUser.getPassword())){
            log.info(new MyException(CodeMsg.SUCCESS).toString());
            return MyRsp.success("登录成功");
        }
        return MyRsp.wrapper(new MyException(CodeMsg.FAIL));
    }

}
