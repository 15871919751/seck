package org.ct.seckill.vo;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * @Author K
 * @Date 2020/1/18 0:54
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginVo {
    @NotBlank(message = "手机号码不能为空")
    @Pattern(regexp = "^1(3|4|5|7|8)\\d{9}$",message = "手机号码格式错误")
    private String mobile;
    @NotBlank(message = "密码不能为空")
    @Length(min = 32)
    private String password;
}
