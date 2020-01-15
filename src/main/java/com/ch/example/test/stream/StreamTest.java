package com.ch.example.test.stream;

import com.ch.example.entity.Demo;
import org.junit.Test;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Author: chenhao
 * @Date: 2020/1/2 16:44
 */
public class StreamTest {
    private static List<DemoModel> empList=null;
    static {
        empList = Arrays.asList(new DemoModel(102, "李四", 59, 6666.66, DemoModel.Status.BUSY),
                new DemoModel(101, "张三", 18, 9999.99, DemoModel.Status.FREE),
                new DemoModel(103, "王五", 28, 3333.33, DemoModel.Status.VOCATION),
                new DemoModel(104, "赵六", 8, 7777.77, DemoModel.Status.BUSY),
                new DemoModel(106, "赵六", 8, 7777.77, DemoModel.Status.FREE),
                new DemoModel(105, "赵六", 8, 7777.77, DemoModel.Status.FREE),
                new DemoModel(105, "田七", 38, 5555.55, DemoModel.Status.BUSY));
    }


    /**
     * stream 只有return 相当于for的continue，没有break操作
     * 没有打断退出这一说法
     */
    @Test
    public void testFor(){
        AtomicBoolean flag= new AtomicBoolean(false);
        empList.stream().forEach(emp->{
            if(emp.getId()==101){
                flag.set(true);
                return;
            }
            if(emp.getId()==102){
                System.out.println(111);
            }
        });
        System.out.println(flag);
    }

    @Test
    public void testFor2(){
        /**
         * 代替for循环 使用匹配或者过滤
         */
        boolean result= empList.stream().anyMatch(obj->obj.getId()==101);
        System.out.println(result);

        Optional<DemoModel> result2=empList.stream().filter(obj->obj.getId()==101).findFirst();
        System.out.println(result2.get());
    }

    /**
     * 过滤器 filter
     */
    @Test
    public void testFilter(){
        empList.stream().filter(obj->obj.getId()==101||obj.getId()==102).forEach(System.out::println);
    }

    /**
     * 跳过 前n条数据
     */
    @Test
    public void testSkip(){
        empList.stream().skip(1).forEach(System.out::println);
    }

    /**
     * 去重
     */
    @Test
    public void testDistinct(){
        empList.stream().distinct().forEach(System.out::println);
    }

    /**
     * 限制 只取前几条数据
     */
    @Test
    public void testLimit(){
        empList.stream().limit(2).forEach(System.out::println);
    }

    /**
     * 映射到map
     */
    @Test
    public void testMap(){
        empList.stream().map(DemoModel::getId).collect(Collectors.toList()).forEach(System.out::println);
    }

    @Test
    public void testList(){
        empList.stream().map(obj->new DemoModel().setId(obj.getId()+1).setAge(obj.getAge()+100)).collect(Collectors.toList()).forEach(System.out::println);
    }

    /**
     * 倒序
     */
    @Test
    public void testSort(){
        empList.stream().sorted((d1,d2)->(d2.getAge()-d1.getAge())).collect(Collectors.toList()).forEach(System.out::println);
    }

    /**
     * 倒序
     */
    @Test
    public void testSort2(){
        empList.stream().sorted(Comparator.comparing(DemoModel::getAge).reversed()).forEach(System.out::println);
    }

    /**
     * 正序
     */
    @Test
    public void testSortOrder(){
        empList.stream().sorted(Comparator.comparing(DemoModel::getAge)).forEach(System.out::println);
    }

    /**
     * 多条件排序
     */
    @Test
    public void testSort3(){
        empList.stream().sorted(Comparator.comparing(DemoModel::getAge).thenComparing(DemoModel::getId)).forEach(System.out::println);
        System.out.println("------------");
        empList.stream().sorted(Comparator.comparing(DemoModel::getAge).thenComparing(DemoModel::getId)).forEach(System.out::println);
        System.out.println("------------");
        empList.stream().sorted(Comparator.comparing(DemoModel::getAge).reversed().thenComparing(DemoModel::getId)).forEach(System.out::println);
    }


    /**
     * 聚合
     */
    @Test
    public void testReduce(){
        empList.stream().reduce((a,b)->{
            if(a.getId()==b.getId()){
                return a;
            }
            return null;
        }).get().toString();
    }

    @Test
    public void test(){
        System.out.println(new String(Base64.getDecoder().decode("7aC87b2D")));
    }

}
