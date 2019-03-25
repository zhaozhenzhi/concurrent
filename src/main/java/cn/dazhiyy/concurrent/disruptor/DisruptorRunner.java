package cn.dazhiyy.concurrent.disruptor;

import cn.dazhiyy.concurrent.disruptor.producer.EventDataProducer;
import cn.dazhiyy.concurrent.disruptor.quickstart.EventData;
import cn.dazhiyy.concurrent.disruptor.quickstart.EventDataFactory;
import cn.dazhiyy.concurrent.disruptor.quickstart.EventDataHandler;
import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import java.nio.ByteBuffer;
import java.util.concurrent.Executors;

/**
 * @author dazhi
 * @projectName concurrent-start
 * @packageName cn.dazhiyy.concurrent.disruptor
 * @className DisruptorRunner
 * @description TODO
 * @date 2019/3/25 21:44
 */
public class DisruptorRunner {

    public static void main(String[] args) {
        Disruptor<EventData> disruptor = new Disruptor<>(
                new EventDataFactory(),
                1024,
                Executors.newSingleThreadExecutor(),
                ProducerType.MULTI,
                new BlockingWaitStrategy()
                );

        //生产者和消费者的一个关联关系
        disruptor.handleEventsWith(new EventDataHandler());

        //启动disruptor容器
        disruptor.start();

        // 获取实际存储数据的容器 ringBuffer
        RingBuffer<EventData> ringBuffer = disruptor.getRingBuffer();

        EventDataProducer producer = new EventDataProducer(ringBuffer);

        ByteBuffer buffer = ByteBuffer.allocate(8);

        for (int i = 0; i < 100; i++) {
            buffer.putInt(0,i);
            buffer.putInt(1,i+1);
            producer.sender(buffer);
        }

        disruptor.shutdown();
    }
}
