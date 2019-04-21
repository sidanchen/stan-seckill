package com.stan.seckill.service.impl;

import com.stan.seckill.dao.SeckillGoodsDao;
import com.stan.seckill.mq.MQSendService;
import com.stan.seckill.mq.SeckillMessage;
import com.stan.seckill.pojo.Orders;
import com.stan.seckill.pojo.SeckillGoods;
import com.stan.seckill.redis.RedisService;
import com.stan.seckill.service.OrdersService;
import com.stan.seckill.service.SeckillGoodsService;
import com.stan.seckill.util.MessagesUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 秒杀商品服务实现类
 * Created by sdc on 2019/4/7.
 */
@Service
public class SeckillGoodsServiceImpl implements SeckillGoodsService {


    Log log = LogFactory.getLog(SeckillGoodsServiceImpl.class);


    @Autowired
    SeckillGoodsDao seckillGoodsDao;

    @Autowired
    OrdersService ordersService;

    @Autowired
    RedisService redisService;

    @Autowired
    MQSendService mqSendService;

    public static boolean goodsStockInitFlag = true;

    /**
     * 将秒杀商品初始化到redis中
     */
    private void goodsStockInit(){
        SeckillGoods seckillGoods = seckillGoodsDao.querySeckillGoods(1);
        redisService.set(""+seckillGoods.getId(),seckillGoods,0);
        goodsStockInitFlag = false;
    }


    @Override
    public String seckillGoods(int goodsId,int userId) throws Exception {
        try{
            if(goodsStockInitFlag){
                goodsStockInit();
            }
            //查询redis中的库存
            SeckillGoods seckillGoods = redisService.get(""+goodsId,SeckillGoods.class);

            if(seckillGoods == null){
                return MessagesUtil.success("秒杀已经结束！");
            }
            if(seckillGoods.getGoodsStock() <= 0){
                return MessagesUtil.success("商品已被秒杀完，敬请期待下次抢购。");
            }

            SeckillMessage seckillMessage = new SeckillMessage();
            seckillMessage.setGoodsId(goodsId);
            seckillMessage.setUserId(userId);

            //入队
            mqSendService.send(seckillMessage);

            return  MessagesUtil.success("排队中，请等候...");
        }catch(Exception ex){
            log.error("秒杀失败！",ex);
        }
        return MessagesUtil.error("网络连接不稳定，请重新尝试！");
    }
}
