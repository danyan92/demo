package com.ch.example.utils.Redis;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 *
 * @Author chenhao
 * @Description //todo redis 通用工具类
 * @Date 2019/3/6
 **/
@Component
public class RedisUtil {

    /**
     * 过期时间
     */
    private static int TIME_OUT=60*3600;

    public static final String LOCK_PREFIX = "redis_lock";
    //ms
    //业务处理时间
    public static final int LOCK_EXPIRE = 300;


    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public Object get(String key) {
        return key == null ? null : redisTemplate.opsForValue().get(key);
    }

    public boolean set(String key, Object value) {
        try {
            redisTemplate.opsForValue().set(key, JSON.toJSON(value),TIME_OUT, TimeUnit.SECONDS);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean del(String key){
        try {
            return redisTemplate.delete(key);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //分布式redis锁
    //缺点 需要设置业务过期时间
    public boolean lock(String key){

        String lock = LOCK_PREFIX + key;
        // 利用lambda表达式
        return (Boolean) redisTemplate.execute((RedisCallback) connection -> {
            //过期时间
            long expireAt = System.currentTimeMillis() + LOCK_EXPIRE + 1;
            Boolean acquire = connection.setNX(lock.getBytes(), String.valueOf(expireAt).getBytes());
            if (acquire) {
                return true;
            } else {

                byte[] value = connection.get(lock.getBytes());

                if (Objects.nonNull(value) && value.length > 0) {

                    long expireTime = Long.parseLong(new String(value));

                    if (expireTime < System.currentTimeMillis()) {
                        // 如果锁已经过期
                        // getset的原子性
                        //如果该命令返回的结果跟上一步通过get获得的过期时间一致，则说明这两步之间，没有新的客户端抢占了锁，则该客户端即获得锁。
                        //如果该命令返回的结果跟上一步通过get获得的过期时间不一致，则该锁可能已被其他客户端抢先获得，则本次获取锁失败。
                        //防止高并发的情况下同时获取到锁
                        byte[] oldValue = connection.getSet(lock.getBytes(), String.valueOf(System.currentTimeMillis() + LOCK_EXPIRE + 1).getBytes());
                        // 防止死锁
                        return Long.parseLong(new String(oldValue)) < System.currentTimeMillis();
                    }
                }
            }
            return false;
        });
    }


}
