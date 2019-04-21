package com.stan.seckill.mq;

import com.stan.config.mqconfig.QueueConfig;
import com.stan.seckill.dao.OrdersDao;
import com.stan.seckill.dao.SeckillGoodsDao;
import com.stan.seckill.pojo.Orders;
import com.stan.seckill.pojo.SeckillGoods;
import com.stan.seckill.redis.RedisService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by sdc on 2019/4/21.
 */
@Service
public class MQReceiver {

    Log log = LogFactory.getLog(MQReceiver.class);

    @Autowired
    SeckillGoodsDao seckillGoodsDao;

    @Autowired
    OrdersDao ordersDao;

    @RabbitListener(queues = QueueConfig.SCKILL_QUEUE)
    public void receive(String seckillMessageString){
        SeckillMessage seckillMessage =
                RedisService.stringToBean(seckillMessageString,SeckillMessage.class);


        //判断数据库中的库存
        SeckillGoods seckillGoods = seckillGoodsDao.querySeckillGoods(seckillMessage.getGoodsId());


        if(seckillGoods.getGoodsStock() <= 0){
            log.info("userId:" + seckillMessage.getUserId() + ",goodsId:" + seckillMessage.getGoodsId()
                    +",商品已经秒杀完毕");
            return;
        }
        //判断用户是否已经秒杀到

        Orders orders =
                ordersDao.isExistsOrders(seckillMessage.getGoodsId(),seckillMessage.getUserId());

        if(orders != null){
            log.info("userId:" + seckillMessage.getUserId() + "goodsId:" + seckillMessage.getGoodsId() + "，已经秒杀到！");
            return;
        }

        //减库存
        seckillGoodsDao.updateSeckillGoodsStock(1,seckillMessage.getGoodsId());

        //入库
        orders = new Orders();
        orders.setUserId(seckillMessage.getUserId());
        orders.setGoodsId(seckillMessage.getGoodsId());

        ordersDao.insertOrders(orders);


    }
}
