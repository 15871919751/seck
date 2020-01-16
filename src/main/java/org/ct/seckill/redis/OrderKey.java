package org.ct.seckill.redis;

/**
 * @Author K
 * @Date 2020/1/17 0:12
 * @Version 1.0
 */
public class OrderKey extends BasePrefix {

    public OrderKey(int expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }
}
