package cn.dazhiyy.concurrent.disruptor.quickstart;

import com.lmax.disruptor.EventHandler;

/**
 * @author dazhi
 * @projectName concurrent-start
 * @packageName cn.dazhiyy.concurrent.disruptor.quickstart
 * @className EventDataHandler
 * @description TODO
 * @date 2019/3/25 22:02
 */
public class EventDataHandler implements EventHandler<EventData> {


    @Override
    public void onEvent(EventData event, long sequence, boolean endOfBatch) throws Exception {
        System.out.println("eventData:"+event);

        Thread.sleep(100L);
    }
}
