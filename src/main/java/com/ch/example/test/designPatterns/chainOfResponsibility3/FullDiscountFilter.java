package com.ch.example.test.designPatterns.chainOfResponsibility3;

import com.ch.example.test.designPatterns.chainOfResponsibility3.DiscountFilter;

/**
 * @Author: chenhao
 * @Date: 2019/11/7 16:38
 * @Desc:满200减20元
 */
public class FullDiscountFilter implements CalculateMethod {
    @Override
    public int calculateBySourcePrice(int price) {
        if (price > 200){
            System.out.println("优惠满减20元");
            price = price - 20;
        }
        return price;
    }
}
