package org.ct.seckill.redis;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;

import java.io.Serializable;

/**
 * @Author K
 * @Date 2020/1/16 22:32
 * @Version 1.0
 */

public interface KeyPrefix  {
   /**
    * 获取key值前缀
    *
    * */
     String getPrefix();

    /**
     * 设置失效时间
     * */
     Integer expireSeconds();

}
