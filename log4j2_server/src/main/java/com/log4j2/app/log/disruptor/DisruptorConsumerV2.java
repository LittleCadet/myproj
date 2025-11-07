package com.log4j2.app.log.disruptor;

import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.WorkHandler;

/**
 * @author shenxie
 * @date 2024/2/28
 */
public class DisruptorConsumerV2 implements WorkHandler<DisruptorEvent> {
    @Override
    public void onEvent(DisruptorEvent event) throws Exception {
        System.out.println("disruptorConsumer消费到了event:" + event);
    }
}
