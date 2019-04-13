package cn.dazhiyy.concurrent.disruptor.quickstart;

import cn.dazhiyy.concurrent.disruptor.producer.Trade;
import com.lmax.disruptor.EventFactory;

/**
 * @author dazhi
 * @projectName concurrent-start
 * @packageName cn.dazhiyy.concurrent.disruptor.quickstart
 * @className TradeFactroy
 * @description TODO
 * @date 2019/3/26 21:53
 */
public class TradeFactroy implements EventFactory<Trade> {

    @Override
    public Trade newInstance() {
        return new Trade();
    }
}
