package org.ct.seckill.controller;


import lombok.extern.slf4j.Slf4j;
import org.ct.seckill.common.CodeMsg;
import org.ct.seckill.domain.SecKillUser;
import org.ct.seckill.service.SeckillUserService;
import org.ct.seckill.util.ValidatorUtil;
import org.ct.seckill.vo.LoginExecution;
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
    public LoginExecution doLogin(LoginVo loginVo) {
        String mobile = loginVo.getMobile();
        String password = loginVo.getPassword();
        log.info(loginVo.toString());
        //参数校验
        if (StringUtils.isBlank(mobile)) {
            return new LoginExecution(CodeMsg.MOBILE_EMPTY);
        } else if (StringUtils.isBlank(password)) {
            return new LoginExecution(CodeMsg.PASSWORD_EMPTY);
        }else  if (!ValidatorUtil.isMobile(mobile)) {
            return new LoginExecution(CodeMsg.MOBILE_ERROR);
        }
        SecKillUser secKillUser = seckillUserService.getUser(Long.valueOf(mobile));
        log.info("get mobile -> {}", mobile);
        log.info("secKillUser -> {}", secKillUser.toString());
        if (mobile.equals(secKillUser.getId()+"")&&password.equals(secKillUser.getPassword())){
            log.info(new LoginExecution(CodeMsg.SUCCESS).toString());
            return new LoginExecution(CodeMsg.SUCCESS);
        }
        log.info(new LoginExecution(CodeMsg.FAIL).toString());
        return  new LoginExecution(CodeMsg.FAIL);
    }

}
