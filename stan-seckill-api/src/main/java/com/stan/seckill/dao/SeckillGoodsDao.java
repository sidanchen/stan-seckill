package com.stan.seckill.dao;

import com.stan.seckill.pojo.SeckillGoods;
import org.apache.ibatis.annotations.Param;

/**
 * Created by sdc on 2019/4/7.
 */
public interface SeckillGoodsDao {

    /**
     * 查询秒杀商品
     * @param goodsId 商品id
     * @return
     */
    SeckillGoods querySeckillGoods(@Param("goodsId") int goodsId);

    /**
     * 更新秒杀商品库存
     * @param seckillGoodsNumber 客户秒杀的数量
     * @return
     */
    int updateSeckillGoodsStock(@Param("seckillGoodsNumber") int seckillGoodsNumber,
                                @Param("goodsId") int goodsId);
}
