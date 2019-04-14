package com.stan.config.redis;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;


/**
 * jedis配置
 * Created by sdc on 2019/4/14.
 */
@Configuration
public class RedisConfig {

    @Value("${spring.redis.host}")
    private String host;
    @Value("${spring.redis.port}")
    private int port;
    @Value("${spring.redis.timeout}")
    private int timeout;//秒
    @Value("${spring.redis.password}")
    private String password;
    @Value("${spring.redis.pool.max-active}")
    private int poolMaxActive;
    @Value("${spring.redis.pool.max-idle}")
    private int poolMaxIdle;
    @Value("${spring.redis.pool.max-wait}")
    private int poolMaxWait;//秒

    Log log = LogFactory.getLog(RedisConfig.class);

    @Bean
    public JedisPool jedisPool(){
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxIdle(poolMaxIdle);
        poolConfig.setMaxTotal(poolMaxActive);
        poolConfig.setMaxWaitMillis(poolMaxWait * 1000);
        JedisPool jp = new JedisPool(poolConfig, host, port,
                timeout * 1000, password, 0);
        log.info("JedisPool init success....");
        return jp;
    }
}
