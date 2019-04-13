package cn.dazhiyy.concurrent.disruptor.factory;

import java.util.concurrent.ThreadFactory;

/**
 * @author dazhi
 * @projectName concurrent-start
 * @packageName cn.dazhiyy.concurrent.disruptor.factory
 * @className SingeThreadFactory
 * @description TODO
 * @date 2019/3/26 22:01
 */
public class SimpleThreadFactory implements ThreadFactory {




    @Override
    public Thread newThread(Runnable r) {
        return null;
    }
}
