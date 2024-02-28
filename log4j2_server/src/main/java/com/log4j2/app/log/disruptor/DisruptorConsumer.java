package com.log4j2.app.log.disruptor;

import com.lmax.disruptor.EventHandler;

/**
 * @author shenxie
 * @date 2024/2/28
 */
public class DisruptorConsumer implements EventHandler<DisruptorEvent> {
    @Override
    public void onEvent(DisruptorEvent event, long sequence, boolean endOfBatch) throws Exception {
        System.out.println("disruptorConsumer消费到了event:" + event);
    }
}
