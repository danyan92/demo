package com.ch.example.test.designPatterns.singleton;

/**
 * 懒汉 线程不安全
 * @Author: chenhao
 * @Date: 2019/4/28 19:09
 */
public class Singleton2 {
    private static Singleton2 singleton2;

    private Singleton2(){

    }

    private static Singleton2 get(){
        if (singleton2 == null) {
            singleton2= new Singleton2();
        }
      return singleton2;
    }

    public static void main(String[] args) {
        System.out.println(Singleton2.get()==(Singleton2.get()));
    }

}
