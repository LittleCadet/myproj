package com.log4j2.app.log.disruptor;

import com.lmax.disruptor.EventTranslator;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author shenxie
 * @date 2024/2/28
 */
@Service
public class DisruptorPublish {

    @Autowired
    private Disruptor<DisruptorEvent> customDisruptor;

    public void publish(){
        customDisruptor.publishEvent(new EventTranslator<DisruptorEvent>() {
            @Override
            public void translateTo(DisruptorEvent event, long sequence) {
                event.setAnything("xxxx");
            }
        });
    }

    public void publishV2(){
        RingBuffer<DisruptorEvent> ringBuffer = customDisruptor.getRingBuffer();
        long sequence = ringBuffer.next();
        DisruptorEvent disruptorEvent = ringBuffer.get(sequence);
        disruptorEvent.setAnything("sequence");
        ringBuffer.publish(sequence);
    }
}
