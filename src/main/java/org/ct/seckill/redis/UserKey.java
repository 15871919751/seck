package org.ct.seckill.redis;

/**
 * @Author K
 * @Date 2020/1/17 0:08
 * @Version 1.0
 */

public class UserKey extends BasePrefix {

    private UserKey( String prefix) {
        super(prefix);
    }

    public static final UserKey getById = new UserKey("id");
    public static final UserKey getByName = new UserKey("name");

}
