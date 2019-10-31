package com.ch.example.utils.Redis;

import org.redisson.RedissonRedLock;
import org.redisson.api.RBucket;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * 互斥性。在任意时刻，只有一个客户端能持有锁。
 *
 * 不会发生死锁。即使有一个客户端在持有锁的期间崩溃而没有主动解锁，也能保证后续其他客户端能加锁。
 *
 * 具有容错性。只要大部分的Redis节点正常运行，客户端就可以加锁和解锁。
 *
 * 加锁和解锁必须是同一个客户端，客户端自己不能把别人加的锁给解了。
 *
 * redlock
 *
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

    public void test(){
        RLock lock= redissonClient.getLock("test");
        RedissonRedLock redLock=new RedissonRedLock(lock);
        try {
            if(redLock.tryLock()){
                Thread.sleep(500);
            }
        }catch (Exception e){
            //ignore
        }finally {
            redLock.unlock();
        }

    }
}
