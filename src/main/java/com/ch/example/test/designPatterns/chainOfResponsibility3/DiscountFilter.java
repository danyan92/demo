package com.ch.example.test.designPatterns.chainOfResponsibility3;


/**
 * FullDistcountFliter满200减20元;
 * FirstPurchaseDiscount首次购买减20元;
 * SecondPurchaseDiscountFilter第二件打9折;
 * @Author: chenhao
 * @Date: 2019/11/7 16:37
 */
public interface DiscountFilter {


    int calculate1(int price);

}
