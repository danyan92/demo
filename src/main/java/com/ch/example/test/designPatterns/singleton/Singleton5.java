package com.ch.example.test.designPatterns.singleton;

/**
 * 静态内部类
 * @Author: chenhao
 * @Date: 2019/4/28 19:29
 */
public class Singleton5 {

    private static class SingletonHolder {
        private static final  Singleton5 singleton5=new Singleton5();
    }

    private static Singleton5 get(){
        return SingletonHolder.singleton5;
    }

    public static void main(String[] args) {
        System.out.println(Singleton5.get()==Singleton5.get());
    }


}
