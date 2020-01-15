package com.ch.example.test.competableFuture;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

/**
 * 异步操作
 * @Author: chenhao
 * @Date: 2019/4/24 13:37
 */
public class CompletableFutureTest {
    @Test
    public void test1() throws ExecutionException, InterruptedException {
        ExecutorService executorService=Executors.newFixedThreadPool(10);

        CompletableFuture<Integer> a=CompletableFuture.supplyAsync(()->
        {
            return 1;
        },executorService).exceptionally(e->{
            return 2;
        });


        CompletableFuture<Integer> b=CompletableFuture.supplyAsync(()->{
            try {
                Thread.sleep(2000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("b完成");
            return 11;
        },executorService);

        Integer result=a.get();

        if(result==1){
            b.complete(2);
           // b.cancel(true);
        }
        Integer result2=b.get();
        System.out.println(result+"-------------"+result2);
    }

    @Test
    public void test2(){
        // 结果集
        List<String> list = new ArrayList<>();
        ExecutorService executorService=Executors.newFixedThreadPool(10);
        List<Integer> taskList = Arrays.asList(2, 1, 3, 4, 5, 6, 7, 8, 9, 10);
        // 全流式处理转换成CompletableFuture[]+组装成一个无返回值CompletableFuture，join等待执行完毕。返回结果whenComplete获取
        CompletableFuture[] cfs = taskList.stream()
                .map(integer -> CompletableFuture.supplyAsync(() -> calc(integer), executorService)
                        .thenApply(h->Integer.toString(h))
                        .whenComplete((s, e) -> {
                            System.out.println("任务"+s+"完成!result="+s+"，异常 e="+e+","+new Date());
                            list.add(s);
                        })
                ).toArray(CompletableFuture[]::new);
        // 封装后无返回值，必须自己whenComplete()获取
        CompletableFuture.allOf(cfs).join();
    }

    public int calc(Integer i) {
        try {
            if (i == 1) {
                Thread.sleep(3000);//任务1耗时3秒
            } else if (i == 5) {
                Thread.sleep(5000);//任务5耗时5秒
            } else {
                Thread.sleep(1000);//其它任务耗时1秒
            }
            System.out.println("task线程：" + Thread.currentThread().getName()
                    + "任务i=" + i + ",完成！+" + new Date());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return i;
    }
}
