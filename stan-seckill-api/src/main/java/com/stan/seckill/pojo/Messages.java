package com.stan.seckill.pojo;

import lombok.Data;

/**
 * Created by sdc on 2019/4/7.
 */
@Data
public class Messages {

    public final static int ERROR_NUMBER = 0;
    public final static int SUCCESS_NUMBER = 1;


    private int status;
    private String message;
    private Object Object;

}
