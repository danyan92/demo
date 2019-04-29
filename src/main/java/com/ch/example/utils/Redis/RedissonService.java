package com.ch.example.utils.Redis;

import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author: chenhao
 * @Date: 2019/4/29 19:40
 */
@Component
public class RedissonService {

    @Autowired
    private RedissonClient redissonClient;

    public <T> RBucket<T> getRBucket(String objectName) {
        RBucket<T> bucket = redissonClient.getBucket(objectName);
        return bucket;
    }
}
