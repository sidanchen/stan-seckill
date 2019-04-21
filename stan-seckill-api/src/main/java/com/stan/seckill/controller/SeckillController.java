package com.stan.seckill.controller;

import com.stan.seckill.service.SeckillGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

/**
 * 秒杀controller类
 * Created by sdc on 2019/4/7.
 */
@RestController
@RequestMapping("/seckill-api")
public class SeckillController {

    @Autowired
    SeckillGoodsService seckillGoodsService;

    @RequestMapping("/seckill/{goodsId}")
    public String seckill(@PathVariable("goodsId") int goodsId) throws Exception{
        //使用时间模拟用户id
        Random random = new Random();
        int userId = random.nextInt(10000);
        return seckillGoodsService.seckillGoods(goodsId,userId);
    }
}
