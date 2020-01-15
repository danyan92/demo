package test;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.redis.util.RedisLockRegistry;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Lock;

/**
 * @Author: chenhao
 * @Date: 2020/1/14 16:12
 */
public class SpringRedisLockRegistryTest extends AbstractMockService {

    private static CountDownLatch latch = new CountDownLatch(2);

    @Autowired
    private RedisLockRegistry redisLockRegistry;

    @Test
    public void testLock() throws InterruptedException {
        Thread t1=new Thread(new T1());
        Thread t2=new Thread(new T2());
        t1.start();
        t2.start();
        latch.await();
    }

    @Test
    public void testTryLock() throws InterruptedException {
        Thread t1=new Thread(new T3());
        Thread t2=new Thread(new T4());
        t1.start();
        t2.start();
        latch.await();
    }

    class T1 implements Runnable{
        @Override
        public void run() {
            Lock lock= redisLockRegistry.obtain("lock_key");
            try {
                System.out.println(LocalDateTime.now()+":1开始获取锁");
                lock.lock();
                System.out.println(LocalDateTime.now()+":1上锁成功");
                Thread.sleep(3000);
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
            Lock lock= redisLockRegistry.obtain("lock_key");
            try {
                System.out.println(LocalDateTime.now()+":2开始获取锁");
                lock.lock();
                System.out.println(LocalDateTime.now()+":2上锁成功");
                Thread.sleep(1000);
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
            Lock lock= redisLockRegistry.obtain("lock_key");
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
            Lock lock= redisLockRegistry.obtain("lock_key");
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
