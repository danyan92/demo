package com.ch.example.test.designPatterns.factory;

/**
 * @Author: chenhao
 * @Date: 2019/11/7 14:58
 */
public class Handler1 extends EventHandler {

    private static final String type="1";

    @Override
    public String type() {
        return type;
    }

    @Override
    public void doSomething() {
        System.out.println("11111");
    }
}
