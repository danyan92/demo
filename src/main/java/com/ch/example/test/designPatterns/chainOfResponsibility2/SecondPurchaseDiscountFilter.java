package com.ch.example.test.designPatterns.chainOfResponsibility2;

/**
 * @Author: chenhao
 * @Date: 2019/11/7 16:40
 */
public class SecondPurchaseDiscountFilter  extends DiscountFilter{
    @Override
    public int calculateBySourcePrice(int price) {

        System.out.println("第二件打9折");
        Double balance =  price * 0.9;

        if(this.nextDiscountFilter != null) {
            return super.nextDiscountFilter.calculateBySourcePrice(balance.intValue());
        }
        return price;
    }
}