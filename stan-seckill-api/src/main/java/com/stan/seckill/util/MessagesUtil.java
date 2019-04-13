package com.stan.seckill.util;

import com.alibaba.fastjson.JSON;
import com.stan.seckill.pojo.Messages;

/**
 * Created by sdc on 2019/4/7.
 */
public class MessagesUtil {


    public static String success(String message){
        Messages messages = new Messages();
        messages.setStatus(Messages.SUCCESS_NUMBER);
        messages.setMessage(message);
        return JSON.toJSONString(messages);
    }

    public static String error(String message){
        Messages messages = new Messages();
        messages.setStatus(Messages.ERROR_NUMBER);
        messages.setMessage(message);
        return JSON.toJSONString(messages);
    }
}
