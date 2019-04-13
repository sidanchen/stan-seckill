package com.stan.seckill.service;

import com.stan.seckill.pojo.Orders;

/**
 * 订单服务接口
 * Created by sdc on 2019/4/7.
 */
public interface OrdersService {

    /**
     * 新增订单信息
     * @param orders 订单信息
     * @return
     */
    boolean insertOrders(Orders orders) throws Exception;

    /**
     * 判断用户是否已经秒杀到了商品
     * @param goodsId
     * @param userId
     * @return
     */
    Orders isExistsOrders(int goodsId,int userId)throws Exception;
}
