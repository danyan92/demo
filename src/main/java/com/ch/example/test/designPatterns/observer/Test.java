package com.ch.example.test.designPatterns.observer;

/**
 * @Author: chenhao
 * @Date: 2019/11/7 18:27
 */
public class Test {
    public static void main(String[] args) {
        ConcreteObserver observer=new ConcreteObserver();
        ConcreteSubject subject1=new ConcreteSubject("a",observer);
        observer.send("1111");
        ConcreteSubject subject2=new ConcreteSubject("b",observer);
        observer.send("1111");
    }
}
