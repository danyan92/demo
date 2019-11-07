package com.ch.example.test.designPatterns.chainOfResponsibility2;

/**
 * FullDistcountFliter满200减20元;
 * FirstPurchaseDiscount首次购买减20元;
 * SecondPurchaseDiscountFilter第二件打9折;
 * HolidayDiscountFilter节日一律减5元.
 * @Author: chenhao
 * @Date: 2019/11/7 16:37
 */
public abstract class DiscountFilter {
    // 下一个责任链成员
    protected DiscountFilter nextDiscountFilter;

    // 根据原价计算优惠后的价格
    public abstract int calculateBySourcePrice(int price);
}
