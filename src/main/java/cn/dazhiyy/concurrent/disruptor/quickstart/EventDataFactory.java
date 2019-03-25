package cn.dazhiyy.concurrent.disruptor.quickstart;

import com.lmax.disruptor.EventFactory;

/**
 * @author dazhi
 * @projectName concurrent-start
 * @packageName cn.dazhiyy.concurrent.disruptor.quickstart
 * @className EventDataFactory
 * @description TODO
 * @date 2019/3/25 21:59
 */

public class EventDataFactory implements EventFactory<EventData> {

    @Override
    public EventData newInstance() {
        return new EventData();
    }
}
