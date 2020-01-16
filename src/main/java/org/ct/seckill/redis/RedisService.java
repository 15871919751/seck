package org.ct.seckill.redis;

import com.alibaba.fastjson.JSON;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;


@Component
@Configuration
@SpringBootTest
public class RedisService {

    @Autowired
    @Qualifier(value = "jedisPool")
    private JedisPool jedisPool;

    public <T> T get(String key, Class<T> clazz) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String str = jedis.get(key);
            return stringToBean(str, clazz);
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

    public <T> boolean set(String key, T value) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String str = beanToString(value);
            if (str == null || str.length() <= 0) {
                return false;
            } else {
                jedis.set(key, str);
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
