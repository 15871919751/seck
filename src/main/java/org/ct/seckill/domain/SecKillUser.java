package org.ct.seckill.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.util.Date;



/**
 * @Author K
 * @Date 2020/1/19 0:41
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SecKillUser implements Serializable {
    private Long id;
    /** 真实姓名*/
    private String nickname;
    /** 密码*/
    private String password;
    /** 加盐字符串*/
    private String salt;
    /** 头像 云储存的ID*/
    private String head;
    /** 注册时间*/
    private Date registerDate;
    /** 最后登录时间*/
    private Date lastLoginDate;
    /** 登录次数*/
    private Integer loginCount;
}
