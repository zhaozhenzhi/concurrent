package cn.dazhiyy.concurrent.disruptor.consumer;

import cn.dazhiyy.concurrent.disruptor.data.ContainerEvent;
import com.lmax.disruptor.WorkHandler;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author dazhi
 * @projectName concurrent-start
 * @packageName cn.dazhiyy.concurrent.disruptor.consumer
 * @className Consumer
 * @description TODO
 * @date 2019/3/27 23:21
 */
@Data
@Slf4j
public class WorkConsumer implements WorkHandler<ContainerEvent> {

    private String consumerId;

    private static AtomicInteger count = new AtomicInteger(0);

    private Random random = new Random();



    @Override
    public void onEvent(ContainerEvent event) throws Exception {
        Thread.sleep(random.nextInt(5));
        log.info("当前消费者:{},消费信息ID:{}",Thread.currentThread().getName(),event.getId());
        count.incrementAndGet();
    }


    public static int getCount(){
        return count.get();
    }
}
