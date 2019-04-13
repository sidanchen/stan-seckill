package com.stan.seckill.service.impl;

import com.stan.seckill.dao.OrdersDao;
import com.stan.seckill.pojo.Orders;
import com.stan.seckill.service.OrdersService;
import com.stan.seckill.util.MessagesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 订单服务接口实现类
 * Created by sdc on 2019/4/7.
 */
@Service
public class OrdersServiceImpl implements OrdersService {

    @Autowired
    OrdersDao ordersDao;

    /**
     * 新增订单信息 当没有新增成功时抛出异常
     * @param orders 订单信息
     * @return
     * @throws Exception
     */
    @Override
    public boolean insertOrders(Orders orders) throws Exception {
        try{
            boolean flag =  ordersDao.insertOrders(orders);
            if(!flag){
                throw new Exception("新增订单出错！");
            }
            return true;
        }catch(Exception ex){
            //logger.error("新增订单信息出错！",ex);
            throw  ex;
        }
    }

    @Override
    public Orders isExistsOrders(int goodsId, int userId)throws Exception {
        try{
            return ordersDao.isExistsOrders(goodsId,userId);
        }catch(Exception ex){
            //logger.error("查询用户是否已经秒杀商品出错！",ex);
            throw ex;
        }
    }
}
