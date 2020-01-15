package com.ch.example.test.分布式uuid;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: chenhao
 * @Date: 2020/1/14 10:12
 */
public class AtomicIntegerUtil {

    public static final int incrementAndGet(AtomicInteger atomicInteger){
        int current;
        int next;
        do {
            current = atomicInteger.get();
            next = current >= 2147483647?0:current + 1;
        } while(!atomicInteger.compareAndSet(current, next));

        return next;
    }
}
