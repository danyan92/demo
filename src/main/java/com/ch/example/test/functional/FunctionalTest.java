package com.ch.example.test.functional;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.function.*;

/**
 * 函数式编程
 * @Author: chenhao
 * @Date: 2019/4/25 13:47
 */
public class FunctionalTest {
    @Test
    public void test1(){
//        do1("1","2",(a,b)->b);


        do2(1,2,(a,b)->a+b);

        do3(Arrays.asList(new String[]{"1","2","3"}),n-> System.out.println(n));
    }

    private void do1(String a,String b,BiFunction<String,String,String> consumer){
//        System.out.println(consumer.test());
        System.out.println(consumer.apply(a,b));
    }

    private void do2(double a, double b, DoubleBinaryOperator doubleBinaryOperator){
        System.out.println(doubleBinaryOperator.applyAsDouble(a,b));
    }


    private void do3(List<String> list, Consumer consumer){
        list.forEach(str->{
            consumer.accept(str);
        });
    }
}
