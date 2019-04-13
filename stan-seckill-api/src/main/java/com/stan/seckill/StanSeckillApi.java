package com.stan.seckill;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by sdc on 2019/4/7.
 */
@SpringBootApplication
@ComponentScan("com.stan")
@MapperScan("com.stan.*.dao")
@EnableTransactionManagement
public class StanSeckillApi {
    public static void main(String[] args){
        SpringApplication.run(StanSeckillApi.class);
    }
}
