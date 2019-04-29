package com.ch.example.test.designPatterns.singleton;

/**
 * 枚举
 * @Author: chenhao
 * @Date: 2019/4/28 19:33
 */
public enum Singleton6 {
    SINGLETON6;

    Singleton6(){};

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static void main(String[] args) {
        System.out.println(Singleton6.SINGLETON6==Singleton6.SINGLETON6);
        Singleton6 s1= Singleton6.SINGLETON6;
        s1.setName("111");
        System.out.println(s1.getName());
        Singleton6 s2= Singleton6.SINGLETON6;
        s2.setName("222");
        System.out.println(s1.getName());
        System.out.println(s2.getName());
    }
}
