package org.ct.seckill.redis;

/**
 * @Author K
 * @Date 2020/1/16 22:32
 * @Version 1.0
 */

public interface KeyPrefix  {
   /**
    * 获取key值前缀
    * @return 返回 key前缀
    * */
     String getPrefix();

    /**
     * 设置失效时间
     * @return  返回key的有效时间 0永不过期
     * */
     int expireSeconds();

}
