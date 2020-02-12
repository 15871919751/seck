package org.ct.seckill.config;

import org.ct.seckill.access.AccessInterceptor;
import org.ct.seckill.access.UserLocal;
import org.ct.seckill.domain.SecKillUser;
import org.ct.seckill.service.SeckillUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;


/**
 * @Author K
 * @Date 2020/1/26 14:52
 * @Version 1.0
 */
@Service
public class UserArgumentResolver implements HandlerMethodArgumentResolver {
    @Autowired
    SeckillUserService seckillUserService;

    @Autowired
    AccessInterceptor accessInterceptor;

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        Class<?> clazz = methodParameter.getParameterType();
        return SecKillUser.class == clazz;
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        return UserLocal.getUser();
    }
}

