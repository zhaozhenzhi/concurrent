package cn.dazhiyy.concurrent.disruptor.quickstart;

import lombok.Data;

/**
 * @author dazhi
 * @projectName concurrent-start
 * @packageName cn.dazhiyy.concurrent.disruptor.quickstart
 * @className EventData
 * @description TODO
 * @date 2019/3/25 21:57
 */
@Data
public class EventData {

    private String key;

    private String value;
}
