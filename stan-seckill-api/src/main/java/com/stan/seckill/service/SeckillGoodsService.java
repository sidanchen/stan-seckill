package com.stan.seckill.service;

/**
 * 秒杀商品服务接口
 * Created by sdc on 2019/4/7.
 */
public interface SeckillGoodsService {

    /**
     * 商品秒杀
     * @param goodsId 商品id
     * @return
     */
    String seckillGoods(int goodsId,int userId) throws Exception;
}
