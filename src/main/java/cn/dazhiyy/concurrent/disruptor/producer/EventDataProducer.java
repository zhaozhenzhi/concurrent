package cn.dazhiyy.concurrent.disruptor.producer;

import cn.dazhiyy.concurrent.disruptor.quickstart.EventData;
import com.lmax.disruptor.RingBuffer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.nio.ByteBuffer;

/**
 * @author dazhi
 * @projectName concurrent-start
 * @packageName cn.dazhiyy.concurrent.disruptor.producer
 * @className EventDataProducer
 * @description TODO
 * @date 2019/3/25 22:29
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventDataProducer {

    /**
     * 1.一个环
     * 2.一个首尾相接的数组
     * 3.有一个序号
     * 4.数组中的元素会被提前new出来，生产者 只是向元素填充数据
     *
     */
    RingBuffer<EventData> ringBuffer;


    public void sender(ByteBuffer data) {
        // 获得一个序列
        long sequence = ringBuffer.next();

        // 通过序列获得一个队列队列对象
        try{

            EventData eventData = ringBuffer.get(sequence);

            eventData.setKey(Integer.toString(data.getInt(1)));
            eventData.setValue(Integer.toString(data.getInt(2)));
        } finally {
            // 发布提交
            ringBuffer.publish(sequence);
        }
    }
}
