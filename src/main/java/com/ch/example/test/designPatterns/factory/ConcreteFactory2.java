package com.ch.example.test.designPatterns.factory;

/**
 * @Author: chenhao
 * @Date: 2019/4/29 11:00
 */
public class ConcreteFactory2 {
    Product.AbstractProductA createProductA() {
        return new ProductA2();
    }

    Product.AbstractProductB createProductB() {
        return new ProductB2();
    }
}
