package com.ch.example.test.designPatterns.chainOfResponsibility3;

import com.ch.example.test.designPatterns.chainOfResponsibility3.DiscountFilter;

/**
 * @Author: chenhao
 * @Date: 2019/11/7 16:39
 */
public class FirstPurchaseDiscount implements CalculateMethod{
    @Override
    public int calculateBySourcePrice(int price) {
        if (price > 100){
            System.out.println("首次购买减20元");
            price = price - 20;
        }
        return price;
    }
}
