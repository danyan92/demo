package com.ch.example.test.loadBalancAlgorithm;

import org.junit.Test;

import java.util.*;

/**
 * 加权随机法跟加权轮询法类似，根据后台服务器不同的配置和负载情况，
 * 配置不同的权重。不同的是，它是按照权重来随机选取服务器的，而非顺序。
 * @Author: chenhao
 * @Date: 2019/12/25 10:51
 */
public class WeightRandom {
    private static Map<String, Integer> serviceWeightMap = new HashMap<String, Integer>();

    static {
        serviceWeightMap.put("192.168.1.100", 1);
        serviceWeightMap.put("192.168.1.101", 2);
        serviceWeightMap.put("192.168.1.102", 3);
        serviceWeightMap.put("192.168.1.103", 4);
    }

    public static String testWeightRandom() {
        // 重新创建一个map，避免出现由于服务器上线和下线导致的并发问题
        Map<String, Integer> serverMap = new HashMap<String, Integer>();
        serverMap.putAll(serviceWeightMap);

        //取得IP地址list
        Set<String> keySet = serverMap.keySet();
        List<String> serverList = new ArrayList<String>();
        Iterator<String> it = keySet.iterator();

        while (it.hasNext()) {
            String server = it.next();
            Integer weight = serverMap.get(server);
            for (int i=0; i<weight; i++) {
                serverList.add(server);
            }
        }
        Random random = new Random();
        int randomPos = random.nextInt(serverList.size());
        String server = serverList.get(randomPos);
        return server;
    }

    @Test
    public void test(){
        System.out.println(testWeightRandom());
    }

}
