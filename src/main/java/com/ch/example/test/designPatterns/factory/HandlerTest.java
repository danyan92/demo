package com.ch.example.test.designPatterns.factory;

/**
 * @Author: chenhao
 * @Date: 2019/11/7 14:59
 */
public class HandlerTest {
    public static void main(String[] args) {
        EventHandler.register(new Handler1());
        EventHandler.register(new Handler2());
        EventHandler handler=EventHandler.getHandler(new Event("1"));
        handler.doSomething();
        EventHandler handler2=EventHandler.getHandler(new Event("2"));
        handler2.doSomething();
    }
}
