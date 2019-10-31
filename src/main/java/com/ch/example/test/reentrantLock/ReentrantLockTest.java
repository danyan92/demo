package com.ch.example.test.reentrantLock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * fair or unfair
 * @Author: chenhao
 * @Date: 2019/8/2 13:50
 */
public class ReentrantLockTest {

    //static ReentrantLock lock = new ReentrantLock(true);
    static Lock lock = new ReentrantLock(true);
    static Condition condition=lock.newCondition();

    public static void main(String[] args) throws InterruptedException {
        for(int i=0;i<5;i++){
            new Thread(new ThreadDemo(i)).start();
        }
    }

    static class ThreadDemo implements Runnable {
        Integer id;

        public ThreadDemo(Integer id) {
            this.id = id;
        }

        @Override
        public void run() {
            for(int i=0;i<2;i++){
                lock.lock();
                try {
                    condition.await(5,TimeUnit.SECONDS);
                    Thread.sleep(1000);
                    condition.signal();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("获得锁的线程："+id);
                lock.unlock();
            }
        }
    }

}
