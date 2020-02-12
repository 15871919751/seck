package org.ct.seckill.access;

import org.ct.seckill.domain.SecKillUser;

/**
 * @Author K
 * @Date 2020/2/8 17:07
 * @Version 1.0
 */
public class UserLocal {

    private static ThreadLocal<SecKillUser> userLocal = new ThreadLocal<>();

    public static void setUser(SecKillUser user) {
        userLocal.set(user);
    }

    public static SecKillUser getUser() {
        return userLocal.get();
    }
}
