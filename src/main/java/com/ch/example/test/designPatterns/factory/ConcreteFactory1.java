package com.ch.example.test.designPatterns.factory;

/**
 * @Author: chenhao
 * @Date: 2019/4/29 10:45
 */
public class ConcreteFactory1 extends AbstractFactory {

    @Override
    Product.AbstractProductA createProductA() {
        return new ProductA1();
    }

    @Override
    Product.AbstractProductB createProductB() {
        return new ProductB1();
    }

}
