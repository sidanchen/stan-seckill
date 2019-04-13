package com.stan.seckill.pojo;

import lombok.Data;

/**
 * 订单类
 * Created by sdc on 2019/4/7.
 */
@Data
public class Orders {
    private int id;
    private int userId;
    private int goodsId;
}
