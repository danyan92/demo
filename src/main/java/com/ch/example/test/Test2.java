package com.ch.example.test;

import org.junit.Test;

/**
 * @Author: chenhao
 * @Date: 2019/4/24 14:47
 */
public class Test2 {
    @Test
    public void test1(){
        System.out.println(DateUtils.getCurrentDateTimeStr());
        System.out.println(DateUtils.parseLocalDateTime("2019-04-28 14:00:00","yyyy-MM-dd HH:mm:ss"));
        System.out.println(DateUtils.formatLocalDateTime(DateUtils.parseLocalDateTime("2019-04-28 14:00:00","yyyy-MM-dd HH:mm:ss"),"yyyy"));
        System.out.println(DateUtils.formatLocalDateTime(DateUtils.parseLocalDateTime("2019-04-28 14:00:00","yyyy-MM-dd HH:mm:ss"),"yyyy"));

        System.out.println(DateUtils.getCurrentLocalTime());
    }
}
