package com.ch.example.test.designPatterns.chainOfResponsibility2;

/**
 * @Author: chenhao
 * @Date: 2019/11/7 16:38
 * @Desc:满200减20元
 */
public class FullDiscountFilter extends DiscountFilter {
    @Override
    public int calculateBySourcePrice(int price) {
        if (price > 200){
            System.out.println("优惠满减20元");
            price = price - 20;
        }
        if(this.nextDiscountFilter != null) {
            return super.nextDiscountFilter.calculateBySourcePrice(price);
        }
        return price;
    }
}
