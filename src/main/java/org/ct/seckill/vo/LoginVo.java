package org.ct.seckill.vo;

import lombok.*;

/**
 * @Author K
 * @Date 2020/1/18 0:54
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class LoginVo {
    private String mobile;
    private String password;
}
