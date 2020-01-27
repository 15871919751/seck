package org.ct.seckill.config;

import org.ct.seckill.domain.SecKillUser;
import org.ct.seckill.service.SeckillUserService;
import org.ct.seckill.service.impl.SeckillUserServiceImpl;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author K
 * @Date 2020/1/26 14:52
 * @Version 1.0
 */
@Service
public class UserArgumentResolver implements HandlerMethodArgumentResolver {
    @Autowired
    SeckillUserService seckillUserService;

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        Class<?> clazz = methodParameter.getParameterType();
        return SecKillUser.class == clazz;
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        HttpServletRequest request = nativeWebRequest.getNativeRequest(HttpServletRequest.class);
        HttpServletResponse response =nativeWebRequest.getNativeResponse(HttpServletResponse.class);
        String cookieToken = request.getParameter(SeckillUserServiceImpl.COOKIE_NAME_TOKEN);
        String paramToken = getCookieValue(request,SeckillUserServiceImpl.COOKIE_NAME_TOKEN);
        if (StringUtils.isBlank(cookieToken) && StringUtils.isBlank(paramToken)) {
            return "login";
        }
        String token = StringUtils.isBlank(paramToken) ? cookieToken : paramToken;
       return seckillUserService.getByToken(response, token);
    }

    private String getCookieValue(HttpServletRequest request, String cookieName) {
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie:cookies) {
            if (cookie.getName().equals(cookieName)) {
                return cookie.getValue();
            }
        }
        return null;
    }
}
