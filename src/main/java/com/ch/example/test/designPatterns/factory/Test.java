package com.ch.example.test.designPatterns.factory;

/**
 * @Author: chenhao
 * @Date: 2019/4/29 11:01
 */
public class Test {
    public static void main(String[] args) {
        AbstractFactory abstractFactory = new ConcreteFactory1();
        Product.AbstractProductA productA = abstractFactory.createProductA();
        Product.AbstractProductB productB = abstractFactory.createProductB();
        // do something with productA and productB
    }
}
