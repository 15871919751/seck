package org.ct.seckill.util;

import java.util.UUID;

/**
 * @Author K
 * @Date 2020/1/23 15:04
 * @Version 1.0
 */
public class UUIDUtil {
    public static String uuid() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
