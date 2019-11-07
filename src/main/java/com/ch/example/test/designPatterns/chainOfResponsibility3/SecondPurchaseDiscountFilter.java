package com.ch.example.test.designPatterns.chainOfResponsibility3;

/**
 * @Author: chenhao
 * @Date: 2019/11/7 16:40
 */
public class SecondPurchaseDiscountFilter  implements CalculateMethod{
    @Override
    public int calculateBySourcePrice(int price) {
        System.out.println("第二件打9折");
        return (int) (price * 0.9);
    }
}