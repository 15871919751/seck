package org.ct.seckill.dao;

import org.ct.seckill.domain.SecKillUser;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author K
 * @Date 2020/1/19 0:47
 * @Version 1.0
 */
public interface SecKillUserDao extends JpaRepository<SecKillUser, Long> {

}
