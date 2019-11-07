package com.ch.example.test.designPatterns.chainOfResponsibility3;

/**
 * @Author: chenhao
 * @Date: 2019/11/7 16:58
 */
public class Test {
    public static void main(String[] args) {
        DiscountChain chain=new DiscountChain();
        chain.add(new FirstPurchaseDiscount()).add(new FullDiscountFilter()).add(new SecondPurchaseDiscountFilter());
        int result=chain.calculate1(240);
        System.out.println(result);
    }
}
