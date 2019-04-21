package com.stan.seckill.redis;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * Created by sdc on 2019/4/21.
 */
@Component
public class RedisService {

    @Autowired
    JedisPool jedisPool;


    /**
     * 获取当个对象
     * */
    public <T> T get(String key,  Class<T> clazz) {
        Jedis jedis = null;
        try {
            jedis =  jedisPool.getResource();
            //生成真正的key
            String realKey  = key;
            String  str = jedis.get(realKey);
            T t =  stringToBean(str, clazz);
            return t;
        }finally {
            returnToPool(jedis);
        }
    }

    public  Long expice(String key,int exTime){
        Jedis jedis = null;
        Long result = null;
        try {
            jedis =  jedisPool.getResource();
            result = jedis.expire(key,exTime);
            return result;
        } finally {
            returnToPool(jedis);
        }
    }

    /**
     * 设置对象
     * */
    public <T> boolean set( String key,  T value ,int exTime) {
        Jedis jedis = null;
        try {
            jedis =  jedisPool.getResource();
            String str = beanToString(value);
            if(str == null || str.length() <= 0) {
                return false;
            }
            //生成真正的key
            String realKey  = key;
            if(exTime == 0) {
                //直接保存
                jedis.set(realKey, str);
            }else {
                //设置过期时间
                jedis.setex(realKey, exTime, str);
            }
            return true;
        }finally {
            returnToPool(jedis);
        }
    }
    public  Long del(String key){
        Jedis jedis = null;
        Long result = null;
        try {
            jedis =  jedisPool.getResource();
            result = jedis.del(key);
            return result;
        } finally {
            returnToPool(jedis);
        }
    }
    /**
     * 判断key是否存在
     * */
    public <T> boolean exists(String key) {
        Jedis jedis = null;
        try {
            jedis =  jedisPool.getResource();
            //生成真正的key
            String realKey  = key;
            return  jedis.exists(realKey);
        }finally {
            returnToPool(jedis);
        }
    }

    /**
     * 增加值
     * */
    public <T> Long incr(String key) {
        Jedis jedis = null;
        try {
            jedis =  jedisPool.getResource();
            //生成真正的key
            String realKey  = key;
            return  jedis.incr(realKey);
        }finally {
            returnToPool(jedis);
        }
    }

    /**
     * 减少值
     * */
    public <T> Long decr(String key) {
        Jedis jedis = null;
        try {
            jedis =  jedisPool.getResource();
            //生成真正的key
            String realKey  =  key;
            return  jedis.decr(realKey);
        }finally {
            returnToPool(jedis);
        }
    }

    /**
     * bean 转 String
     * @param value
     * @param <T>
     * @return
     */
    public static <T> String beanToString(T value) {
        if(value == null) {
            return null;
        }
        Class<?> clazz = value.getClass();
        if(clazz == int.class || clazz == Integer.class) {
            return ""+value;
        }else if(clazz == String.class) {
            return (String)value;
        }else if(clazz == long.class || clazz == Long.class) {
            return ""+value;
        }else {
            return JSON.toJSONString(value);
        }
    }


    /**
     * string转bean
     * @param str
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T stringToBean(String str, Class<T> clazz) {
        if(str == null || str.length() <= 0 || clazz == null) {
            return null;
        }
        if(clazz == int.class || clazz == Integer.class) {
            return (T)Integer.valueOf(str);
        }else if(clazz == String.class) {
            return (T)str;
        }else if(clazz == long.class || clazz == Long.class) {
            return  (T)Long.valueOf(str);
        }else {
            return JSON.toJavaObject(JSON.parseObject(str), clazz);
        }
    }

    private void returnToPool(Jedis jedis) {
        if(jedis != null) {
            jedis.close();
        }
    }
}



