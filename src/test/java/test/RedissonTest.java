package test;

import org.junit.Test;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.concurrent.CountDownLatch;

/**
 * @Author: chenhao
 * @Date: 2020/1/14 16:59
 */
public class RedissonTest extends AbstractMockService {
    private static CountDownLatch latch = new CountDownLatch(2);

    private static final String lock_name="lock";

    @Autowired
    private RedissonClient redissonClient;

    @Test
    public void testLock() throws InterruptedException {
        Thread t1=new Thread(new RedissonTest.T1());
        Thread t2=new Thread(new RedissonTest.T2());
        t1.start();
        t2.start();
        latch.await();
    }

    @Test
    public void testTryLock() throws InterruptedException {
        Thread t1=new Thread(new RedissonTest.T3());
        Thread t2=new Thread(new RedissonTest.T4());
        t1.start();
        t2.start();
        latch.await();
    }

    class T1 implements Runnable{
        @Override
        public void run() {
            RLock lock=redissonClient.getLock(lock_name);
            try {
                System.out.println(LocalDateTime.now()+":1开始获取锁");
                lock.lock();
                System.out.println(LocalDateTime.now()+":1上锁成功");
                Thread.sleep(3000);
                System.out.println(LocalDateTime.now()+":1end");
                latch.countDown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                lock.unlock();
            }

        }
    }

    class T2 implements Runnable{
        @Override
        public void run() {
            RLock lock=redissonClient.getLock(lock_name);
            try {
                System.out.println(LocalDateTime.now()+":2开始获取锁");
                lock.lock();
                System.out.println(LocalDateTime.now()+":2上锁成功");
                Thread.sleep(3000);
                System.out.println(LocalDateTime.now()+":2end");
                latch.countDown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                lock.unlock();
            }

        }
    }


    class T3 implements Runnable{
        @Override
        public void run() {
            RLock lock=redissonClient.getLock(lock_name);
            try {
                System.out.println(LocalDateTime.now()+":3开始获取锁");
                if(lock.tryLock()){
                    System.out.println(LocalDateTime.now()+":3获取锁成功");
                    Thread.sleep(3000);
                }else{
                    Thread.sleep(1000);
                    System.out.println(LocalDateTime.now()+":3获取锁失败");
                }
                System.out.println(LocalDateTime.now()+":3end");
                latch.countDown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                if(lock.tryLock()){
                    lock.unlock();
                }
            }
        }
    }

    class T4 implements Runnable{
        @Override
        public void run() {
            RLock lock=redissonClient.getLock(lock_name);
            try {
                System.out.println(LocalDateTime.now()+":4开始获取锁");
                if(lock.tryLock()){
                    System.out.println(LocalDateTime.now()+":4获取锁成功");
                    Thread.sleep(3000);
                }else{
                    Thread.sleep(1000);
                    System.out.println(LocalDateTime.now()+":4获取锁失败");
                }
                System.out.println(LocalDateTime.now()+":4end");
                latch.countDown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                if(lock.tryLock()){
                    lock.unlock();
                }
            }
        }
    }
}
