package com.ch.example.test.designPatterns.singleton;

/**
 * 懒汉 线程安全 但是是阻塞的
 * @Author: chenhao
 * @Date: 2019/4/28 19:14
 */
public class Singleton3 {
    private static Singleton3 singleton3;

    private Singleton3(){

    }

    private static synchronized Singleton3 get(){
        if(singleton3==null){
            singleton3=new Singleton3();
        }
        return singleton3;
    }


    public static void main(String[] args) {
        System.out.println(Singleton3.get()==(Singleton3.get()));
    }
}
