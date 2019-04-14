package com.stan;

import com.stan.seckill.StanSeckillApi;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * Created by sdc on 2019/4/14.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {StanSeckillApi.class})
public class RedisTest {
    @Autowired
    JedisPool jedisPool;
    @Test
    public void testRedis(){
        Jedis jedis= jedisPool.getResource();
        jedis.set("csd","111");
    }
}
