package com.ch.example.test.designPatterns.chainOfResponsibility2;

/**
 * @Author: chenhao
 * @Date: 2019/11/7 16:39
 */
public class FirstPurchaseDiscount extends DiscountFilter{
    @Override
    public int calculateBySourcePrice(int price) {
        if (price > 100){
            System.out.println("首次购买减20元");
            price = price - 20;
        }

        if(this.nextDiscountFilter != null) {
            return super.nextDiscountFilter.calculateBySourcePrice(price);
        }
        return price;
    }
}
