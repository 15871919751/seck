package org.ct.seckill.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.ct.seckill.dao.SecKillUserDao;
import org.ct.seckill.domain.SecKillUser;
import org.ct.seckill.service.SeckillUserService;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

/**
 * @Author K
 * @Date 2020/1/19 1:22
 * @Version 1.0
 */

@Slf4j
@Service
public class SeckillUserServiceImpl implements SeckillUserService {

    @Autowired
    private final SecKillUserDao secKillUserDao;

    public SeckillUserServiceImpl(SecKillUserDao secKillUserDao) {
        this.secKillUserDao = secKillUserDao;
    }

    @Override
    public SecKillUser getUser(Long id) {
        if (StringUtils.isNotBlank(String.valueOf(id))&&id>0) {
            log.debug("getUser by id -> {}",id);
            Optional<SecKillUser> secKillUser = secKillUserDao.findById(id);
            return secKillUser.orElse(SecKillUser.invaild());
        }
        return SecKillUser.invaild();
    }
}
