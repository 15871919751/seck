package org.ct.seckill.redis;

/**
 * @Author K
 * @Date 2020/1/17 0:08
 * @Version 1.0
 */

public class GoodsKey extends BasePrefix {

    private GoodsKey(int expireSeconds, String prefix) {
        super(expireSeconds,prefix);
    }
    /**
     * 秒杀商品页面KEY
     * */
    public static final GoodsKey GOODS_KEY = new GoodsKey(60, "gl");
    /**
     * 秒杀商品库存KEY
     * */
    public static final GoodsKey SEC_KILL_COUNT = new GoodsKey(0,"sec");
    /**
     * 秒杀商品详情KEY
     * */
    public static final GoodsKey SECKILL_GOODS_DETAIL = new GoodsKey(0,"sgd");

}
