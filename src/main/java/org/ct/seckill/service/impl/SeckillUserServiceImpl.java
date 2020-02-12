package org.ct.seckill.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.ct.seckill.common.CodeMsg;
import org.ct.seckill.common.MyException;
import org.ct.seckill.common.MyRsp;
import org.ct.seckill.dao.SecKillUserDao;
import org.ct.seckill.domain.SecKillUser;
import org.ct.seckill.redis.RedisService;
import org.ct.seckill.redis.RedisSeckillUser;
import org.ct.seckill.redis.UserKey;
import org.ct.seckill.service.SeckillUserService;
import org.ct.seckill.util.UUIDUtil;
import org.ct.seckill.vo.LoginVo;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;


/**
 * @Author K
 * @Date 2020/1/19 1:22
 * @Version 1.0
 */

@Slf4j
@Service
public class SeckillUserServiceImpl implements SeckillUserService {
    public static final String COOKIE_NAME_TOKEN = "token";
    @Autowired
    private final SecKillUserDao secKillUserDao;
    @Autowired
    RedisService redisService;

    public SeckillUserServiceImpl(SecKillUserDao secKillUserDao) {
        this.secKillUserDao = secKillUserDao;
    }

    @Override
    public Object login(HttpServletResponse response, @Valid LoginVo loginVo) {
        if (loginVo == null) {
            return MyRsp.wrapper(new MyException(CodeMsg.FAIL));
        }
        //取缓存
        SecKillUser secKillUser = redisService.get(UserKey.GET_BY_ID, loginVo.getMobile(), SecKillUser.class);
        if (secKillUser == null) {
             secKillUser  = secKillUserDao.getById(Long.valueOf(loginVo.getMobile()));
            if (secKillUser==null) {
                return MyRsp.wrapper(new MyException(CodeMsg.MOBILE_ERROR));
            }
        }
        redisService.set(UserKey.GET_BY_ID, loginVo.getMobile(), secKillUser);
        String mobile = String.valueOf(secKillUser.getId());
        String password = secKillUser.getPassword();
        if (!loginVo.getMobile().equals(mobile)) {
            return MyRsp.wrapper(new MyException(CodeMsg.MOBILE_ERROR));
        }
        if (!loginVo.getPassword().equals(password)) {
            return MyRsp.wrapper(new MyException(CodeMsg.PASSWORD_ERROR));
        }
        //生成cookie
        String token = UUIDUtil.uuid();
        addCookie(response, token,secKillUser);
        return MyRsp.success("登录成功");
    }

    public void addCookie(HttpServletResponse response,String token, SecKillUser secKillUser) {
        redisService.set(RedisSeckillUser.TOKEN, token, secKillUser);
        Cookie cookie = new Cookie(COOKIE_NAME_TOKEN, token);
        cookie.setMaxAge(RedisSeckillUser.TOKEN.expireSeconds());
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    @Override
    public SecKillUser getByToken(HttpServletResponse response, String token) {
        if (StringUtils.isBlank(token)) {
            return null;
        }
        SecKillUser user = redisService.get(RedisSeckillUser.TOKEN, token, SecKillUser.class);
        //延长有效期
        if (user != null) {
            addCookie(response,token, user);
        }
        return user;
    }


}
