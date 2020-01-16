package org.ct.seckill.redis;

import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * @Author K
 * @Date 2020/1/17 0:04
 * @Version 1.0
 */
@AllArgsConstructor
@NoArgsConstructor
public abstract class BasePrefix implements KeyPrefix {

    /**
     * 过期时间
     * */
    private int expireSeconds;

    /**
     * key前缀
     * */
    private String prefix;

    public BasePrefix( String prefix) {
        this(0, prefix);
    }

    @Override
    public String getPrefix() {
        String className = getClass().getSimpleName();
        return className+":"+prefix;
    }

    /**
     * 默认0 代表永不过期
     * */
    @Override
    public Integer expireSeconds() {
        return expireSeconds;
    }
}
