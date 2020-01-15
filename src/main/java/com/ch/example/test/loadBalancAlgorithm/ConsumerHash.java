package com.ch.example.test.loadBalancAlgorithm;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 源地址哈希算法
 * 唯一不丢失策略的算法，但是负载均衡和源数据信息和哈希算法有很大关系。
 *
 * 源地址哈希法的思想是根据服务消费者请求客户端的 IP 地址，通过哈希函数计算得到一个哈希值，
 * 将此哈希值和服务器列表的大小进行取模运算，
 * 得到的结果便是要访问的服务器地址的序号。采用源地址哈希法进行负载均衡，相同的 IP 客户端，
 * 如果服务器列表不变，将映射到同一个后台服务器进行访问。
 * @Author: chenhao
 * @Date: 2019/12/25 9:32
 */
public class ConsumerHash {
    private static Map<String, Integer> serviceWeightMap = new HashMap<String, Integer>();

    static {
        serviceWeightMap.put("192.168.1.100", 1);
        serviceWeightMap.put("192.168.1.101", 1);
        serviceWeightMap.put("192.168.1.102", 4);
        serviceWeightMap.put("192.168.1.103", 1);
    }

    public static String testConsumerHash(String remoteIp) {
        Map<String, Integer> serverMap = new HashMap<String, Integer>();
        serverMap.putAll(serviceWeightMap);

        //取得IP地址list
        Set<String> keySet = serverMap.keySet();
        ArrayList<String> keyList = new ArrayList<String>();
        keyList.addAll(keySet);

        int hashCode = remoteIp.hashCode()&Integer.MAX_VALUE;;
        int pos = hashCode % keyList.size();
        return keyList.get(pos);
    }

    @Test
    public void test(){
        System.out.println(testConsumerHash("192.168.1.111"));
    }


}
