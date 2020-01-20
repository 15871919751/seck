package org.ct.seckill.common;

import lombok.Data;

/**
 * @author K
 */
@Data
public class MyRsp {


    private int state;

    private String msg;

    private Object content;


    public MyRsp(int state, String msg, Object content) {
        this.state = state;
        this.msg = msg;
        this.content = content;
    }

    public static MyRsp wrapper(MyException ex) {
        MyRsp myRsp = new MyRsp();
        myRsp.setState(ex.getState());
        myRsp.setMsg(ex.getMsg());
        myRsp.setContent(null);
        return myRsp;
    }

    public MyRsp() {
    }


    public static MyRsp success(Object content) {
        MyRsp myRsp = new MyRsp();
        myRsp.setState(CodeMsg.SUCCESS.getState());
        myRsp.setMsg(CodeMsg.SUCCESS.getMsg());
        myRsp.setContent(content);

        return myRsp;
    }


    public static MyRsp error() {
        MyRsp myRsp = new MyRsp();
        myRsp.setState(CodeMsg.ERROR.getState());
        myRsp.setMsg(CodeMsg.ERROR.getMsg());
        myRsp.setContent(null);

        return myRsp;
    }

    public MyRsp msg(String msg) {  // 自定义msg  链式函数
        this.msg = msg;
        return this;
    }
}
