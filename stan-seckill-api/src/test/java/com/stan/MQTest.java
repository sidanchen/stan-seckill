package com.stan;


import com.stan.config.mqconfig.QueueConfig;
import com.stan.seckill.StanSeckillApi;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
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
public class MQTest {

    @Autowired
    AmqpTemplate amqpTemplate;

    @Test
    public void testMqSend(){
        amqpTemplate.convertAndSend(QueueConfig.SCKILL_QUEUE,"csd");
    }

    @Test
    public void testRecive(){
        System.out.println("");
        Message message =  amqpTemplate.receive(QueueConfig.SCKILL_QUEUE);
        System.out.println(message.getBody());
    }

}
