package com.ch.example.test.designPatterns.chainOfResponsibility2;

/**
 * 优点
 *
 * 将请求发送者和接收处理者分开。请求者可以不用知道是谁处理的，处理者可以不用知道请求的全貌，两者解耦，提高系统的灵活性。
 * 缺点
 *
 * 性能问题，每个请求都是从链头遍历到链尾，特别是在链比较长的时候，性能是一个非常大的问题；
 * 调试不很方便，特别是链条比较长，类似递归的方式，调试的时候逻辑可能比较复杂。
 * 注意事项
 *
 * 链中节点数量需要控制，避免出现超长链的情况，一般的做法是在Handler中设置一个最大节点数量
 * 在setNext方法中判断是否已经是超过其阈值，超过则不允许该链建立，避免无意识地破坏系统性能。
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
