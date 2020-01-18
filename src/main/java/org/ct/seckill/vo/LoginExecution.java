package org.ct.seckill.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.ct.seckill.common.CodeMsg;


/**
 * @Author K
 * @Date 2020/1/19 0:01
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginExecution {

    private int state;
    private String msg;


    /**
     * 登路密码为空调用的构造方法
     * */
    public LoginExecution(CodeMsg codeMsg) {
        this.state = codeMsg.getState();
        this.msg = codeMsg.getMsg();
    }

}
