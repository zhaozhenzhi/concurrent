package cn.dazhiyy.concurrent.disruptor.factory;

import cn.dazhiyy.concurrent.disruptor.data.ContainerEvent;
import com.lmax.disruptor.EventFactory;

/**
 * @author dazhi
 * @projectName concurrent-start
 * @packageName cn.dazhiyy.concurrent.disruptor.factory
 * @className ContainerEventFactory
 * @description TODO
 * @date 2019/3/27 23:15
 */
public class ContainerEventFactory implements EventFactory<ContainerEvent> {


    @Override
    public ContainerEvent newInstance() {
        return new ContainerEvent();
    }
}
