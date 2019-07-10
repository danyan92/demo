package com.ch.example.test.WebAsyncTask;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.WebAsyncTask;

/**
 * @Author: chenhao
 * @Date: 2019/5/5 14:10
 */
@RestController
public class WebAsyncTaskTest {
    @GetMapping(value = "getUserWithNoThing.do")
    public WebAsyncTask getUserWithNoThing() {
        // 打印处理线程名
        System.err.println("The main Thread name is " + Thread.currentThread().getName());

        // 此处模拟开启一个异步任务,超时时间为10s
        WebAsyncTask task1 = new WebAsyncTask(10 * 1000L, () -> {
            System.err.println("The first Thread name is " + Thread.currentThread().getName());
            // 任务处理时间5s,不超时
            Thread.sleep(5 * 1000L);
            return "任务1顺利执行成功！任何异常都没有抛出！";
        });

        // 任务执行完成时调用该方法
        task1.onCompletion(() -> {
            System.err.println("任务1执行完成啦！");
        });

        System.err.println("task1继续处理其他事情！");
        return task1;
    }

}
