package org.ct.seckill.service;

import org.ct.seckill.domain.SecKillUser;

/**
 * @Author K
 * @Date 2020/1/19 1:22
 * @Version 1.0
 */
public interface SeckillUserService {
  /**
   * 根据id(手机号)获取用户信息
   * @param id 用户的手机号
   * @return 返回一个用户信息
   * */
    SecKillUser getUser(Long id);

}
