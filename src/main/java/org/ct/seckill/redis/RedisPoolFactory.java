package org.ct.seckill.redis;
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

    @Bean(name = "jedisPool")
    public JedisPool jedisPoolFactory(RedisConfig redisConfig) {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(redisConfig.getMaxIdle());
        jedisPoolConfig.setMaxWaitMillis((long)redisConfig.getMaxWait()*1000);
        jedisPoolConfig.setMaxTotal(redisConfig.getMaxActive());
        return new JedisPool(jedisPoolConfig, redisConfig.getHost(), redisConfig.getPort()
                , redisConfig.getTimeout() * 1000, redisConfig.getPassword(), 0);
    }
}

