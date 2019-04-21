package com.stan.seckill.mq;

import com.stan.config.mqconfig.QueueConfig;
import com.stan.seckill.redis.RedisService;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by sdc on 2019/4/21.
 */
@Service
public class MQSendService {

    @Autowired
    AmqpTemplate amqpTemplate;

    public void send(SeckillMessage seckillMessage){
        String seckillMessageString = RedisService.beanToString(seckillMessage);
        amqpTemplate.convertAndSend(QueueConfig.SCKILL_QUEUE,seckillMessageString);
    }
}
