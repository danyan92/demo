package com.ch.example.test.分布式uuid;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 工作id+时间戳+AtomicInteger
 * 工作id通过数据库配置获取/分布式id/mac地址
 * @Author: chenhao
 * @Date: 2020/1/14 9:56
 */
public class UUID2 {

    private static final AtomicInteger COUNT = new AtomicInteger(2147483646);

    public static void main(String[] args) {

        String wordId1="1";
        String wordId2="2";
        String wordId3="3";

        System.out.println(UUID.nameUUIDFromBytes((wordId1+timeGen()+AtomicIntegerUtil.incrementAndGet(COUNT)).getBytes()));
        System.out.println(UUID.nameUUIDFromBytes((wordId2+timeGen()+AtomicIntegerUtil.incrementAndGet(COUNT)).getBytes()));
        System.out.println(UUID.nameUUIDFromBytes((wordId3+timeGen()+AtomicIntegerUtil.incrementAndGet(COUNT)).getBytes()));

    }

    private static long timeGen(){
        return System.currentTimeMillis();
    }
}
