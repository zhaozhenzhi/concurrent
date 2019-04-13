package cn.dazhiyy.concurrent.disruptor.producer;

import cn.dazhiyy.concurrent.disruptor.quickstart.EventData;
import com.lmax.disruptor.RingBuffer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author dazhi
 * @projectName concurrent-start
 * @packageName cn.dazhiyy.concurrent.disruptor.producer
 * @className EventDateProducer1
 * @description TODO
 * @date 2019/3/26 10:14
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventDateProducer1 {


    RingBuffer<EventData> ringBuffer;

    public void sender(EventData data) {
        long sequence = ringBuffer.next();

        try {
            EventData eventData = ringBuffer.get(sequence);
            eventData.setKey(data.getKey());
            eventData.setValue(data.getValue());
        } finally {
            ringBuffer.publish(sequence);
        }

    }
}
