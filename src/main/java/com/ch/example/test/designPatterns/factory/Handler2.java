package com.ch.example.test.designPatterns.factory;

/**
 * @Author: chenhao
 * @Date: 2019/11/7 15:14
 */
public class Handler2 extends EventHandler {

    private static final String type="2";

    @Override
    public String type() {
        return type;
    }

    @Override
    public void doSomething() {
        System.out.println("222222");
    }
}
