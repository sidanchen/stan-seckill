package com.stan.seckill.service.impl;

import com.stan.seckill.dao.SeckillGoodsDao;
import com.stan.seckill.pojo.Orders;
import com.stan.seckill.pojo.SeckillGoods;
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

    @Override
    @Transactional(rollbackFor = Exception.class)
    public synchronized String seckillGoods(int goodsId,int userId) throws Exception {
        //下定单
        Orders orders = null;
        try{
            //查询商品信息
            SeckillGoods seckillGoods = seckillGoodsDao.querySeckillGoods(goodsId);

            //判断商品库存
            if(seckillGoods != null && seckillGoods.getGoodsStock() > 0){
                //判断用户是否已经秒杀
                orders = ordersService.isExistsOrders(goodsId,userId);
                if(orders != null){
                    return MessagesUtil.success("您已经秒杀到该商品请勿重新秒杀！");
                }
                orders = new Orders();
                orders.setGoodsId(goodsId);
                orders.setUserId(userId);

                //库存减1 每个用户只能抢一个
                seckillGoodsDao.updateSeckillGoodsStock(1,goodsId);
                //下订单
                boolean flag = ordersService.insertOrders(orders);
                if(flag){
                    log.info("用户：" + userId +";秒杀商品：" + goodsId + "成功！");
                    return MessagesUtil.success("秒杀成功！");
                }
            }else{
                return MessagesUtil.error("商品已经被秒杀完毕，敬请期待下次秒杀");
            }
        }catch(Exception ex){
            throw new Exception("秒杀出错");
        }

        return MessagesUtil.error("网络连接不稳定，请重新尝试秒杀");
    }
}
