package com.ch.example.test.designPatterns.singleton;

/**
 * 双重校验锁 线程安全
 * volatile 防止重排
 * @Author: chenhao
 * @Date: 2019/4/28 19:04
 */
public class Singleton4 {

    private volatile static Singleton4 singleton4;

    private Singleton4(){

    }

    private static Singleton4 get(){

        if(null==singleton4){
            synchronized (Singleton4.class){
                if(null==singleton4){
                    singleton4=new Singleton4();
                }
            }
        }
        return singleton4;
    }

    public static void main(String[] args) {
        System.out.println(Singleton4.get()==(Singleton4.get()));
    }
}
