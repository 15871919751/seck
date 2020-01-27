package org.ct.seckill.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author K
 * @Date 2020/1/18 23:33
 * @Version 1.0
 */
@Getter
@AllArgsConstructor
public enum CodeMsg {
/**
 *SUCCESS 成功返回success    SERVER_ERROR 服务失败返回服务端异常  SESSION_ERROR Session不存在或者已经失效   PASSWORD_EMPTY 登录密码为空异常
 * */
    SUCCESS(200, "success"), SERVER_ERROR(500100, "服务端异常"), SESSION_ERROR(500210, "Session不存在或者已经失效"),
    PASSWORD_EMPTY(500211, "登录密码不能为空"), MOBILE_EMPTY(500212, "手机号不能为空"), MOBILE_ERROR(500213, "手机号错误")
    , FAIL(500214, "用户名或密码错误"), ERROR(500215, "服务器出小差了"), PASSWORD_ERROR(500216, "密码错误")
    ;
    /**
     * 错误码状态
     */
    private int state;
    /**
     * 错误码详细信息
     * */
    private String msg;
}
