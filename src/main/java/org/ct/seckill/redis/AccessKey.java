package org.ct.seckill.redis;

/**
 * @Author K
 * @Date 2020/2/8 18:07
 * @Version 1.0
 */
public class AccessKey extends BasePrefix{
    private AccessKey(int expireSeconds, String prefix) {
        super(expireSeconds,prefix);
    }

    public static AccessKey withExpire(int expireSeconds) {
        return new AccessKey(expireSeconds,"ak");
    }
}
