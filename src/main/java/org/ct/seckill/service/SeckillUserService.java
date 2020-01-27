package org.ct.seckill.service;

import org.ct.seckill.domain.SecKillUser;
import org.ct.seckill.vo.LoginVo;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * @Author K
 * @Date 2020/1/19 1:22
 * @Version 1.0
 */
public interface SeckillUserService {
  /**
   * 登录的接口根据封装的LoginVo对象中的手机号密码和数据库中的用户手机号密码进行校验
   * @param response 响应对象
   * @param loginVo 用户手机号密码封装对象
   * @return 返回一个用户信息
   * */

  Object login(HttpServletResponse response, @Valid LoginVo loginVo);

  /**
   * 根据token查找一个SeckillUser对象
   * @param response 响应对象
   * @param token  用户的token
   * @return 根据用户的token获取一个用户实体类对象
   * */
  SecKillUser getByToken(HttpServletResponse response,String token);

}
