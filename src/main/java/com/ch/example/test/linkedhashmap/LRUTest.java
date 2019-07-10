package com.ch.example.test.linkedhashmap;

import com.alibaba.fastjson.JSON;


/**
 * least recently use (LRU)
 * @Author: chenhao
 * @Date: 2019/7/10 15:52
 */
public class LRUTest {

    public static void main(String[] args) {
        //accessOrder 为true
        LRUCache cache=new LRUCache(2);
        cache.put("1","1");
        cache.put("2","2");
        //{"1":"1","2":"2"}
        System.out.println(JSON.toJSONString(cache));
        cache.get("1");
        //{"2":"2","1":"1"}
        System.out.println(JSON.toJSONString(cache));
        cache.put("3","3");
        //{"1":"1","3":"3"}
        System.out.println(JSON.toJSONString(cache));
    }
}
