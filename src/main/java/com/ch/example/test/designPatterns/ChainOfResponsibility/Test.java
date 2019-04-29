package com.ch.example.test.designPatterns.ChainOfResponsibility;

/**
 * 设计模式 责任链
 * 使多个对象都有机会处理请求，从而避免请求的发送者和接收者之间的耦合关系。
 * 将这些对象连成一条链，并沿着这条链发送该请求，直到有一个对象处理它为止。
 * @Author: chenhao
 * @Date: 2019/4/29 13:49
 */
public class Test {

    public static void main(String[] args) {
        Handler handler1=new ConcreteHandler1(null);
        Handler handler2=new ConcreteHandler2(handler1);
        handler2.handleRequest(new Request(RequestType.TYPE1,"type1"));
        handler2.handleRequest(new Request(RequestType.TYPE2,"type2"));
    }

}
