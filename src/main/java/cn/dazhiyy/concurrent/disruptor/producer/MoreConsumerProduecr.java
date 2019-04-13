package cn.dazhiyy.concurrent.disruptor.producer;

import cn.dazhiyy.concurrent.disruptor.data.ContainerEvent;
import com.lmax.disruptor.RingBuffer;

/**
 * @author dazhi
 * @projectName concurrent-start
 * @packageName cn.dazhiyy.concurrent.disruptor.producer
 * @className MoreConsumerProduecr
 * @description TODO
 * @date 2019/3/28 00:10
 */
public class MoreConsumerProduecr {

    private RingBuffer<ContainerEvent> buffer;

    public MoreConsumerProduecr(RingBuffer<ContainerEvent> buffer){
        this.buffer = buffer;
    }

    public void sender(ContainerEvent containerEvent){
        long sequence = buffer.next();
        try {
            ContainerEvent containerEvent1 = buffer.get(sequence);
            containerEvent1.setId(containerEvent.getId());
        } finally {
            buffer.publish(sequence);
        }

    }
}
