package com.ch.example.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.RedisFlushMode;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * @Author chenhao
 * @Description //TODO
 * @Date 2019/3/6
 **/
@Configuration
@EnableRedisHttpSession(maxInactiveIntervalInSeconds= 1800, redisFlushMode = RedisFlushMode.ON_SAVE, redisNamespace = "ch")
public class HttpSessionConfig {

}