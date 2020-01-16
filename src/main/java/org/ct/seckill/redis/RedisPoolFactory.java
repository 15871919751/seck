package org.ct.seckill.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;


/**
 * @author K
 */
@Configuration
@Component
public class RedisPoolFactory {

    @Autowired
    private RedisConfig redisConfig;


    @Bean(name = "jedisPool")
    public JedisPool jedisPoolFactory() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(redisConfig.getMaxIdle());
        jedisPoolConfig.setMaxWaitMillis(redisConfig.getMaxWait() * 1000);
        jedisPoolConfig.setMaxTotal(redisConfig.getMaxActive());
        JedisPool jedisPool = new JedisPool(jedisPoolConfig, redisConfig.getHost(), redisConfig.getPort()
                , redisConfig.getTimeout() * 1000, redisConfig.getPassword(), 0);
        return jedisPool;
    }
}

