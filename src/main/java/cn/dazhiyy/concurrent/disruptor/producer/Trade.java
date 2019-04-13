package cn.dazhiyy.concurrent.disruptor.producer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author dazhi
 * @projectName concurrent-start
 * @packageName cn.dazhiyy.concurrent.disruptor.producer
 * @className Trade
 * @description TODO
 * @date 2019/3/26 21:49
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Trade {

    private String id;

    private String name;

    private double price;

    private AtomicInteger count = new AtomicInteger();
}
