package org.ct.seckill.access;

import com.alibaba.fastjson.JSON;
import org.ct.seckill.common.CodeMsg;
import org.ct.seckill.common.MyException;
import org.ct.seckill.common.MyRsp;
import org.ct.seckill.domain.SecKillUser;
import org.ct.seckill.redis.AccessKey;
import org.ct.seckill.redis.MiaoshaKey;
import org.ct.seckill.redis.RedisService;
import org.ct.seckill.service.SeckillUserService;
import org.ct.seckill.service.impl.SeckillUserServiceImpl;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @Author K
 * @Date 2020/2/8 16:41
 * @Version 1.0
 */
@Service
public class AccessInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    SeckillUserService seckillUserService;

    @Autowired
    RedisService redisService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            SecKillUser user = getUser(request, response);
            UserLocal.setUser(user);
            HandlerMethod hm = (HandlerMethod) handler;
            String key = request.getRequestURI();
            AccessLimit accessLimit = hm.getMethodAnnotation(AccessLimit.class);
            if (accessLimit == null) {
                return true;
            }
            int maxSecond = accessLimit.second();
            int maxCount = accessLimit.count();
            boolean needLogin = accessLimit.needLogin();
            //如果需要验证登录
            if (needLogin) {
                if (user == null) {
                    render(response,CodeMsg.SESSION_ERROR);
                    return false;
                }
                key += "_" + user.getId();
                //判断用户信息
            } else {
                //do nothing
            }
            AccessKey ak = AccessKey.withExpire(maxSecond);
            Integer requestUrlCount = redisService.get(ak, key, Integer.class);
            if (requestUrlCount == null) {
                redisService.set(ak, key, 1);
            } else if (requestUrlCount <= maxCount) {
                redisService.incr(ak, key);
            } else {
                render(response, MyRsp.wrapper(new MyException(CodeMsg.REQUEST_ERROR)));
                return false;
            }
        }
        return true;
    }

    private void render(HttpServletResponse response, Object o) {
        try {
            response.setContentType("application/json; charset=UTF-8");
            OutputStream outputStream = response.getOutputStream();
            String sr = JSON.toJSONString(o);
            outputStream.write(sr.getBytes("UTF-8"));
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取用户信息
     * */
    public SecKillUser getUser(HttpServletRequest request,HttpServletResponse response) {
        String cookieToken = request.getParameter(SeckillUserServiceImpl.COOKIE_NAME_TOKEN);
        String paramToken = getCookieValue(request,SeckillUserServiceImpl.COOKIE_NAME_TOKEN);
        if (StringUtils.isBlank(cookieToken) && StringUtils.isBlank(paramToken)) {

        }
        String token = StringUtils.isBlank(paramToken) ? cookieToken : paramToken;
        return seckillUserService.getByToken(response, token);
    }

    private String getCookieValue(HttpServletRequest request, String cookieName) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            return null;
        }
        for (Cookie cookie:cookies) {
            if (cookie.getName().equals(cookieName)) {
                return cookie.getValue();
            }
        }
        return null;
    }

}
