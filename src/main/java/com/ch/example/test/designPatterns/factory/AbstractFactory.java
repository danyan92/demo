package com.ch.example.test.designPatterns.factory;

/**
 * @Author: chenhao
 * @Date: 2019/4/29 10:41
 */
public abstract  class AbstractFactory {
    abstract Product.AbstractProductA createProductA();
    abstract Product.AbstractProductB createProductB();
}
