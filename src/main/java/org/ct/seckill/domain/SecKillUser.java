package org.ct.seckill.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

/**
 * @Author K
 * @Date 2020/1/19 0:41
 * @Version 1.0
 */
@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "miaosha_user")
public class SecKillUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Basic
    @Column(name = "nickname", nullable = false)
    /** 真实姓名*/
    private String nickname;
    @Basic
    @Column(name = "password")
    /** 密码*/
    private String password;
    @Basic
    @Column(name = "salt")
    /** 加盐字符串*/
    private String salt;
    @Basic
    @Column(name = "head")
    /** 头像 云储存的ID*/
    private String head;
    @Basic
    @Column(name = "register_date")
    /** 注册时间*/
    private Date registerDate;
    @Basic
    @Column(name = "last_login_date")
    @CreatedDate
    /** 最后登录时间*/
    private Date lastLoginDate;
    @Basic
    @Column(name = "login_count")
    /** 登录次数*/
    private Integer loginCount;

    public SecKillUser(String nickname, String password, String salt, String head, Date lastLoginDate, Integer loginCount) {
        this.nickname = nickname;
        this.password = password;
        this.salt = salt;
        this.head = head;
        this.lastLoginDate = lastLoginDate;
        this.loginCount = loginCount;
    }

    public static SecKillUser invaild() {
        SecKillUser invaild = new SecKillUser("无效姓名", "无效密码", "", "", new Date(), null);
        invaild.setId(-1L);
        return invaild;
    }
}
