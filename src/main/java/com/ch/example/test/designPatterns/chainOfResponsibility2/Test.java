package com.ch.example.test.designPatterns.chainOfResponsibility2;

/**
 * @Author: chenhao
 * @Date: 2019/11/7 16:40
 */
public class Test {
    public static void main(String[] args) {
        int price = 240;
        String productStr = String.format("商品清单：苹果、香蕉、桔子, 商品总金额为：%d元.", price);
        System.out.println(productStr);
        //声明责任链上的所有节点
        FullDiscountFilter fulDF = new FullDiscountFilter();
        FirstPurchaseDiscount firstDF = new FirstPurchaseDiscount();
        SecondPurchaseDiscountFilter secDF = new SecondPurchaseDiscountFilter();
        //设置链中的顺序:满减->首购减->第二件减
        fulDF.nextDiscountFilter = firstDF;
        firstDF.nextDiscountFilter = secDF;
        secDF.nextDiscountFilter = null;
        int total = fulDF.calculateBySourcePrice(price);
        System.out.println(String.format("所有商品优惠价后金额为:%d", total));
    }
}
