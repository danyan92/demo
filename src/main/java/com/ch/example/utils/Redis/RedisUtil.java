package com.ch.example.utils.Redis;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @Author chenhao
 * @Description //TODO
 * @Date 2019/3/6
 **/
@Component
public class RedisUtil {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public Object get(String key) {
        return key == null ? null : redisTemplate.opsForValue().get(key);
    }

    public boolean set(String key, Object value) {
        try {
            redisTemplate.opsForValue().set(key, JSON.toJSON(value));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
