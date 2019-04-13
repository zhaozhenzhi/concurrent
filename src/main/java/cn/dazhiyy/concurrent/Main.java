package cn.dazhiyy.concurrent;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author dazhi
 * @projectName concurrent-start
 * @packageName cn.dazhiyy.concurrent
 * @className Main
 * @description TODO
 * @date 2019/3/26 14:36
 */
public class Main {

    private static Integer i=0;

    public static void main(String[] args) {

        CountDownLatch latch = new CountDownLatch(1);

        latch.countDown();


    }

    private static void concurrent1() {
        ReentrantLock lock  = new ReentrantLock();

        for (int j = 0; j < 10; j++) {
            final Integer count = j;
            new Thread(()->{
                lock.lock();
                for (int b = 0; b < 100; b++) {
                    System.out.println("当前操作数"+count+",被操作数i:"+i);
                    i=i+b;
                    System.out.println("当前操作数"+count+",被操作数改变成i:"+i);
                }
                lock.unlock();
            }).run();
        }
    }

    private static void concurrent() {
        //ConcurrentHashMap<String,String> map = new ConcurrentHashMap<>();

        //CopyOnWriteArrayList<String> copy = new CopyOnWriteArrayList<>();

        AtomicInteger integer  = new AtomicInteger(1);

        boolean b = integer.compareAndSet(1, 2);
        System.out.println(b);
        System.out.println(integer.get());
    }
}
