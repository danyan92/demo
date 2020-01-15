package com.ch.example.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.integration.redis.util.RedisLockRegistry;

/**
 * @Author: chenhao
 * @Date: 2020/1/14 11:05
 */
@Configuration
public class RedisLockRegistryConfiguration {

    @Bean
    public RedisLockRegistry redisLockRegistry(RedisConnectionFactory factory) {
       return new RedisLockRegistry(factory,"lock_");
    }

}
