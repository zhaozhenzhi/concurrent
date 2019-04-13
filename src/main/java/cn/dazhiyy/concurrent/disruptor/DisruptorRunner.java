package cn.dazhiyy.concurrent.disruptor;

import cn.dazhiyy.concurrent.disruptor.constant.DisruptorConst;
import cn.dazhiyy.concurrent.disruptor.consumer.WorkConsumer;
import cn.dazhiyy.concurrent.disruptor.data.ContainerEvent;
import cn.dazhiyy.concurrent.disruptor.exception.MyEventHandler;
import cn.dazhiyy.concurrent.disruptor.factory.ContainerEventFactory;
import cn.dazhiyy.concurrent.disruptor.producer.EventDataProducer;
import cn.dazhiyy.concurrent.disruptor.producer.EventDateProducer1;
import cn.dazhiyy.concurrent.disruptor.producer.MoreConsumerProduecr;
import cn.dazhiyy.concurrent.disruptor.producer.Trade;
import cn.dazhiyy.concurrent.disruptor.quickstart.EventData;
import cn.dazhiyy.concurrent.disruptor.quickstart.EventDataFactory;
import cn.dazhiyy.concurrent.disruptor.quickstart.EventDataHandler;
import cn.dazhiyy.concurrent.disruptor.quickstart.TradeFactroy;
import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.SequenceBarrier;
import com.lmax.disruptor.WorkerPool;
import com.lmax.disruptor.YieldingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import lombok.extern.slf4j.Slf4j;

import java.nio.ByteBuffer;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;


/**
 * @author dazhi
 * @projectName concurrent-start
 * @packageName cn.dazhiyy.concurrent.disruptor
 * @className DisruptorRunner
 * @description TODO
 * @date 2019/3/25 21:44
 */
@Slf4j
public class DisruptorRunner {

    public static void main(String[] args) throws InterruptedException {
        Disruptor<ContainerEvent> disruptor = new Disruptor<>(
                new ContainerEventFactory(),
                1024,
                Executors.defaultThreadFactory(),
                ProducerType.MULTI,
                new YieldingWaitStrategy()
        );

        WorkConsumer[] consumers = new WorkConsumer[100];
        for (int i = 0; i < 100; i++) {
            consumers[i] = new WorkConsumer();
        }

        disruptor.handleEventsWithWorkerPool(consumers);
        RingBuffer<ContainerEvent> ringBuffer = disruptor.start();

        CountDownLatch latch = new CountDownLatch(1);

        for (int i = 0; i < 100; i++) {
            MoreConsumerProduecr produecr = new MoreConsumerProduecr(ringBuffer);
            new Thread(()-> {
                try {
                    latch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                for (int j = 0; j < 100; j++) {
                    produecr.sender(new ContainerEvent(UUID.randomUUID().toString()));
                }
            }).start();
        }

        Thread.sleep(2000L);
        latch.countDown();

        Thread.sleep(8000L);
        int count = WorkConsumer.getCount();
        log.info(count+"");
    }

    private static void moreComsuer() throws InterruptedException {
        RingBuffer<ContainerEvent> ringBuffer = RingBuffer.create(
                ProducerType.MULTI,
                new ContainerEventFactory(),
                1024,
                new BlockingWaitStrategy()
                );

        // 序号屏障
        SequenceBarrier sequenceBarrier = ringBuffer.newBarrier();

        WorkConsumer[] consumers = new WorkConsumer[100];
        for (int i = 0; i < 100; i++) {
            consumers[i] = new WorkConsumer();
        }


        WorkerPool<ContainerEvent> pool = new WorkerPool<>(
                ringBuffer,
                sequenceBarrier,
                new MyEventHandler(),
                consumers);

        // 获得 消费者的sequences
        ringBuffer.addGatingSequences(pool.getWorkerSequences());
        pool.start(Executors.newFixedThreadPool(5));

        CountDownLatch latch = new CountDownLatch(1);

        for (int i = 0; i < 100; i++) {
            MoreConsumerProduecr produecr = new MoreConsumerProduecr(ringBuffer);
            new Thread(()-> {
               try {
                   latch.await();
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
                for (int j = 0; j < 100; j++) {
                    produecr.sender(new ContainerEvent(UUID.randomUUID().toString()));
                }
            }).start();
        }

        Thread.sleep(2000L);
        latch.countDown();

        Thread.sleep(8000L);
        int count = WorkConsumer.getCount();
        log.info(count+"");
    }

    private static void oldVersion2() throws InterruptedException {
        ThreadFactory factory = Executors.defaultThreadFactory();
        Disruptor<Trade> disruptor = new Disruptor<>(
                new TradeFactroy(),
                DisruptorConst.RING_BUFFER_SIZE,
                factory,
                ProducerType.SINGLE,
                new YieldingWaitStrategy()
        );

        RingBuffer<Trade> start = disruptor.start();

        CountDownLatch latch = new CountDownLatch(1);

        latch.await();

        disruptor.shutdown();
    }

    private static void oldVersion() {
        ThreadFactory factory = Executors.defaultThreadFactory();
        Disruptor<EventData> disruptor = new Disruptor<>(
                new EventDataFactory(),
                DisruptorConst.RING_BUFFER_SIZE,
                factory,
                ProducerType.SINGLE,
                new YieldingWaitStrategy()
        );
        disruptor.handleEventsWith(new EventDataHandler());
        disruptor.handleEventsWith(new EventDataHandler());

        //启动disruptor容器
        disruptor.start();

        RingBuffer<EventData> ringBuffer = disruptor.getRingBuffer();

        EventDateProducer1 producer1 = new EventDateProducer1(ringBuffer);
        for (int i = 0; i < 10000; i++) {
            EventData eventData = new EventData();
            eventData.setKey(i+"");
            eventData.setValue(UUID.randomUUID().toString());
            producer1.sender(eventData);
        }
    }

    private static void oldVersion1() {
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
