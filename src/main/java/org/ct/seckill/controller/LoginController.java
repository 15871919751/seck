package org.ct.seckill.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.ct.seckill.common.CodeMsg;
import org.ct.seckill.common.MyException;
import org.ct.seckill.domain.SecKillUser;
import org.ct.seckill.service.SeckillUserService;
import org.ct.seckill.util.ValidatorUtil;
import org.ct.seckill.vo.LoginVo;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


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
    public MyException doLogin(LoginVo loginVo) {
        String mobile = loginVo.getMobile();
        String password = loginVo.getPassword();
        log.info(loginVo.toString());
        //参数校验
        if (StringUtils.isBlank(mobile)) {
            return new MyException(CodeMsg.MOBILE_EMPTY).msg("手机号不能为空");
        } else if (StringUtils.isBlank(password)) {
            return new MyException(CodeMsg.PASSWORD_EMPTY).msg("密码不能为空");
        }else  if (!ValidatorUtil.isMobile(mobile)) {
            return new MyException(CodeMsg.MOBILE_ERROR).msg("手机号格式错误");
        }
        SecKillUser secKillUser = seckillUserService.getUser(Long.valueOf(mobile));
        log.info("get mobile -> {}", mobile);
        log.info("secKillUser -> {}", secKillUser.toString());
        if (mobile.equals(secKillUser.getId()+"")&&password.equals(secKillUser.getPassword())){
            log.info(new MyException(CodeMsg.SUCCESS).toString());
            return new MyException(CodeMsg.SUCCESS);
        }
        log.info(new MyException(CodeMsg.FAIL).toString());
        return new MyException(CodeMsg.FAIL).msg("发生未知错误");
    }

}
