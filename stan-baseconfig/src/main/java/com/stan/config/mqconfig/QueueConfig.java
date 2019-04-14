package com.stan.config.mqconfig;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by sdc on 2019/4/14.
 */
@Configuration
public class QueueConfig {
    public static final String SCKILL_QUEUE = "sckill_queue";

    @Bean(SCKILL_QUEUE)
    public Queue queue(){
        return new Queue(SCKILL_QUEUE);
    }
}
