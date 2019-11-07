package com.ch.example.test.designPatterns.chainOfResponsibility3;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: chenhao
 * @Date: 2019/11/7 16:53
 */
public class DiscountChain implements DiscountFilter{

    List<CalculateMethod> list = new ArrayList<CalculateMethod>();

    public DiscountChain add(CalculateMethod method) {
        list.add(method);
        return this;
    }

    @Override
    public int calculate1(int price) {
        for(CalculateMethod f : list ){
            price= f.calculateBySourcePrice(price);
        }
        return price;
    }
}
