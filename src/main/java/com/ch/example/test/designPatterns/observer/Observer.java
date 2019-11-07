package com.ch.example.test.designPatterns.observer;

/**
 * 观察者
 * @Author: chenhao
 * @Date: 2019/11/7 18:18
 */
public interface Observer {

    void addSub(ConcreteSubject subject);

    void send(String message);
}
