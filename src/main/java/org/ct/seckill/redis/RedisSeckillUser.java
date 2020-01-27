package org.ct.seckill.redis;

/**
 * @Author K
 * @Date 2020/1/17 0:08
 * @Version 1.0
 */

public class RedisSeckillUser extends BasePrefix {

    private static final int TOKEN_EXPIRE = 3600 * 24 * 2;
    private RedisSeckillUser(int expireSeconds, String prefix) {
        super(expireSeconds,prefix);
    }

    public static final RedisSeckillUser TOKEN = new RedisSeckillUser(TOKEN_EXPIRE,"tk");

}
