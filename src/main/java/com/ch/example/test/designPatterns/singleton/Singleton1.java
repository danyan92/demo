package com.ch.example.test.designPatterns.singleton;

/**
 * 饿汉模式 线程安全 不推荐  没做到延迟加载对于创建对象的消耗
 * @Author: chenhao
 * @Date: 2019/4/28 19:04
 */
public class  Singleton1 {

    private static Singleton1 singleton1=new Singleton1();

    private Singleton1(){

    }

    private static Singleton1 get(){
        return singleton1;
    }

    public static void main(String[] args) {
        System.out.println(Singleton1.get()==(Singleton1.get()));
    }
}
