package com.stan.seckill.mq;

import lombok.Data;

/**
 * Created by sdc on 2019/4/21.
 */
@Data
public class SeckillMessage {
    private int goodsId;
    private int userId;
}
