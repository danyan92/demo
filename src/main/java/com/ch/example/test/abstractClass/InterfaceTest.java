package com.ch.example.test.abstractClass;

/**
 * @Author: chenhao
 * @Date: 2019/4/29 11:10
 */
public interface InterfaceTest {
    void test();

    static void testInterfaceClassStaicMethod(){
        System.out.println("接口测试静态方法");
    }

    public static void main(String[] args) {
        testInterfaceClassStaicMethod();
    }
}
