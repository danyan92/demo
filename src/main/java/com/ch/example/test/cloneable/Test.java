package com.ch.example.test.cloneable;

/**
 * @Author: chenhao
 * @Date: 2019/12/13 15:49
 */
public class Test {
    public static void main(String[] args) throws CloneNotSupportedException {
       DeepCloneableDemo deepCloneableDemo =new DeepCloneableDemo("1",new TestClass("2"));
       DeepCloneableDemo deepCloneableDemo2 = (DeepCloneableDemo) deepCloneableDemo.clone();
       System.out.println(deepCloneableDemo2.getId());
       System.out.println(deepCloneableDemo2.getTestClass().getTest());
       deepCloneableDemo.setId("2");
       deepCloneableDemo.setTestClass(new TestClass("3"));
       System.out.println(deepCloneableDemo2.getId());
       System.out.println(deepCloneableDemo2.getTestClass().getTest());
    }
}
