package org.ct.seckill.common;

import lombok.Data;


/**
 * @author K
 */
@Data
public class MyException extends RuntimeException {


    private int state;
    private String msg;

    /**状态类*/
    public MyException(CodeMsg codeMsg) {
        this.state = codeMsg.getState();
        this.msg = codeMsg.getMsg();
    }



     /**自定义msg  链式函数*/
    public MyException msg(String msg) {
        this.msg = msg;
        return this;
    }
}
