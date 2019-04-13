package com.stan.seckill.dao;

import com.stan.seckill.pojo.Orders;
import org.apache.ibatis.annotations.Param;

/**
 * Created by sdc on 2019/4/7.
 */
public interface OrdersDao {
    /**
     * 添加订单
     * @param orders 订单信息
     * @return
     */
    boolean insertOrders(@Param("orders") Orders orders);

    /**
     * 判断用户是否已经秒杀到了商品
     * @param goodsId
     * @param userId
     * @return
     */
    Orders isExistsOrders(@Param("goodsId") int goodsId,@Param("userId") int userId);
}
