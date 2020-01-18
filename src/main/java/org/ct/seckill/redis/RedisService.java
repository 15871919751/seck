package org.ct.seckill.redis;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;



/**
 * @author K
 */
@Component
@Configuration
public class RedisService {

    @Autowired
    @Qualifier(value = "jedisPool")
    private JedisPool jedisPool;

    public <T> T get(KeyPrefix prefix,String key, Class<T> clazz) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String realKey = prefix.getPrefix() + key;
            String str = jedis.get(realKey);
            return stringToBean(str, clazz);
        } finally {
            returnToPool(jedis);
        }

    }

    /**
     * 判断key是否存在
     * */
    public  Boolean exists(KeyPrefix prefix,String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String realKey = prefix.getPrefix() + key;
            return jedis.exists(realKey);
        } finally {
            returnToPool(jedis);
        }
    }

    public  Long incr(KeyPrefix prefix,String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String realKey = prefix.getPrefix() + key;
            return jedis.incr(realKey);
        } finally {
            returnToPool(jedis);
        }
    }


    public  Long decr(KeyPrefix prefix,String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String realKey = prefix.getPrefix() + key;
            return jedis.decr(realKey);
        } finally {
            returnToPool(jedis);
        }
    }


    private <T> T stringToBean(String str, Class<T> clazz) {
        if (str == null || str.length() <= 0 || clazz == null) {
            return null;
        } else if (clazz == String.class) {
            return (T) str;
        } else if (clazz == int.class || clazz == Integer.class) {
            return (T) Integer.valueOf(str);
        } else if (clazz == long.class || clazz == Long.class) {
            return (T) Long.valueOf(str);
        } else {
            return JSON.parseObject(str, clazz);
        }
    }

    public <T> boolean set(KeyPrefix prefix,String key, T value) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String str = beanToString(value);
            if (str == null || str.length() <= 0) {
                return false;
            } else {
                String realKey = prefix.getPrefix() + key;
                int seconds = prefix.expireSeconds();
                if (seconds <= 0) {
                    jedis.set(realKey, str);
                } else {
                    jedis.setex(realKey, seconds, str);
                }
                return true;
            }
        } finally {
            returnToPool(jedis);
        }
    }

    private <T> String beanToString(T value) {
        if (value == null) {
            return null;
        } else if (value.getClass() == String.class) {
            return value.toString();
        } else if (value.getClass() == int.class || value.getClass() == Integer.class) {
            return "" + value;
        } else if (value.getClass() == long.class || value.getClass() == Long.class) {
            return "" + value;
        } else {
            return JSON.toJSONString(value);
        }

    }

    private void returnToPool(Jedis jedis) {
        if (jedis != null) {
            jedis.close();
        }
    }

}
