package com.ch.example.test.loadBalancAlgorithm;

import org.junit.Test;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 加权轮询算法
 * @Author: chenhao
 * @Date: 2019/12/25 10:53
 */
public class WeightRoundRobin {
    private static Map<String, Integer> serviceWeightMap = new HashMap<String, Integer>();

    private static AtomicInteger pos=new AtomicInteger(0);

    static {
        serviceWeightMap.put("192.168.1.100", 1);
        serviceWeightMap.put("192.168.1.101", 1);
        serviceWeightMap.put("192.168.1.102", 1);
        serviceWeightMap.put("192.168.1.103", 1);
    }


    public static String testWeightRoundRobin() {

        // 重新创建一个map，避免出现由于服务器上线和下线导致的并发问题
        Map<String, Integer> serverMap = new HashMap<String, Integer>();
        serverMap.putAll(serviceWeightMap);

        //取得IP地址list
        Set<String> keySet = serverMap.keySet();
        Iterator<String> it = keySet.iterator();

        List<String> serverList = new ArrayList<String>();

        while (it.hasNext()) {
            String server = it.next();
            Integer weight = serverMap.get(server);
            for (int i=0; i<weight; i++) {
                serverList.add(server);
            }
        }
        if (pos.intValue() >= serverList.size()) {
            pos.getAndSet(0);
        }
        String server = serverList.get(pos.get());
        pos.incrementAndGet();
        return server;
    }

    @Test
    public void test(){
        for(int i=1;i<=20;i++){
            new Thread(new TestThread()).start();
        }

    }


    class TestThread implements Runnable{

        @Override
        public void run() {
            System.out.println(testWeightRoundRobin());
        }
    }
}
