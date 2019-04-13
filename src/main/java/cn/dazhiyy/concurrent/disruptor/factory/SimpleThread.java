package cn.dazhiyy.concurrent.disruptor.factory;

import cn.dazhiyy.concurrent.disruptor.producer.Trade;
import com.lmax.disruptor.dsl.Disruptor;

import java.util.concurrent.CountDownLatch;

/**
 * @author dazhi
 * @projectName concurrent-start
 * @packageName cn.dazhiyy.concurrent.disruptor.factory
 * @className SimpleThread
 * @description TODO
 * @date 2019/3/26 22:15
 */
public class SimpleThread implements Runnable {

    private Disruptor<Trade> disruptor;

    private CountDownLatch latch;

    public SimpleThread(Disruptor<Trade> disruptor,CountDownLatch latch){
        this.disruptor = disruptor;
        this.latch = latch;
    }

    @Override
    public void run() {

    }
}
