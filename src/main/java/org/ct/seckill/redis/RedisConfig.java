package org.ct.seckill.redis;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author K
 */
@ConfigurationProperties(prefix = "redis")
@Component
@Data
public class RedisConfig {

    private String host;
    private Integer port;
    private Integer timeout;
    private Integer maxActive;
    private Integer maxIdle;
    private Integer maxWait;
    private String password;

}
