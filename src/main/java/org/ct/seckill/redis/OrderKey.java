package org.ct.seckill.redis;

/**
 * @Author K
 * @Date 2020/1/17 0:12
 * @Version 1.0
 */
public class OrderKey extends BasePrefix {

    public OrderKey( String prefix) {
        super(prefix);
    }
    public static final OrderKey getMiaoshaOrderByUidGid = new OrderKey("msug");
}
