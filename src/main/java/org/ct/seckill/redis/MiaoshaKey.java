package org.ct.seckill.redis;

import org.junit.platform.commons.util.StringUtils;

/**
 * @Author K
 * @Date 2020/1/17 0:08
 * @Version 1.0
 */

public class MiaoshaKey extends BasePrefix {

    private MiaoshaKey(int expireSeconds, String prefix) {
        super(expireSeconds,prefix);
    }

    /**
     * 库存为空标记Key
     */
    public static final MiaoshaKey GOODS_EMPTY = new MiaoshaKey(0, "ge");
    public static final MiaoshaKey MIAOSHA_PATH = new MiaoshaKey(60, "mp");
    public static final MiaoshaKey VERIFYCODE_KEY = new MiaoshaKey(120, "vk");

}
