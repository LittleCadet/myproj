package com.log4j2.app.log.disruptor.config;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import com.log4j2.app.log.disruptor.DisruptorConsumer;
import com.log4j2.app.log.disruptor.DisruptorEvent;
import java.util.concurrent.ThreadFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author shenxie
 * @date 2024/2/28
 */
@Configuration
public class Config {

    @Bean
    public Disruptor<DisruptorEvent> customDisruptor(){
        Disruptor<DisruptorEvent> disruptor = new Disruptor<>(DisruptorEvent::new, 1024, new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r);
            }
        }, ProducerType.MULTI, new BlockingWaitStrategy());

        // 将EventHandler注册到disruptor。
        disruptor.handleEventsWith(new DisruptorConsumer());

        disruptor.start();
        return disruptor;
    };
}
