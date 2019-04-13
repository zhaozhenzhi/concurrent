package cn.dazhiyy.concurrent.disruptor.exception;

import cn.dazhiyy.concurrent.disruptor.data.ContainerEvent;
import com.lmax.disruptor.ExceptionHandler;

/**
 * @author dazhi
 * @projectName concurrent-start
 * @packageName cn.dazhiyy.concurrent.disruptor.exception
 * @className MyEventHandler
 * @description TODO
 * @date 2019/3/27 23:33
 */
public class MyEventHandler implements ExceptionHandler<ContainerEvent> {
    @Override
    public void handleEventException(Throwable ex, long sequence, ContainerEvent event) {

    }

    @Override
    public void handleOnStartException(Throwable ex) {

    }

    @Override
    public void handleOnShutdownException(Throwable ex) {

    }
}
