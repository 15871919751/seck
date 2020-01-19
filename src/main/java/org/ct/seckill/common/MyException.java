package org.ct.seckill.common;

import lombok.Data;
import lombok.ToString;

/**
 * @author K
 */
@Data
@ToString
public class MyException extends RuntimeException {


    private int code;
    private String msg;

    /**状态类*/
    public MyException(CodeMsg httpCode) {
        this.code = httpCode.getState();
        this.msg = httpCode.getMsg();
    }

     /**自定义msg  链式函数*/
    public MyException msg(String msg) {
        this.msg = msg;
        return this;
    }

}
