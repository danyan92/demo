package com.ch.example.test.message;

import org.junit.Test;

import java.text.MessageFormat;

/**
 * 文本替换 MessageFormat
 * @Author: chenhao
 * @Date: 2019/8/5 15:16
 */
public class MessageTest {

    private static String text="hello {0}, welcome to {1}!";

    @Test
    public void test1(){
        String result=MessageFormat.format(text,"1","2");
        System.out.println(result);
    }





}
