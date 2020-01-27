package org.ct.seckill.dao;


import org.ct.seckill.domain.SecKillUser;

import org.springframework.stereotype.Repository;

/**
 * @Author K
 * @Date 2020/1/19 0:47
 * @Version 1.0
 */

@Repository("secKillUserDao")
public interface SecKillUserDao {
     /**
      * 根据手机号查找用户信息
      * @param mobile 手机号
      * @return 返回一个用户详细信息
      * */
     SecKillUser getById(Long mobile);


}
