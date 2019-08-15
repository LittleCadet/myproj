package com.myproj.app.config;

import com.myproj.app.candp.RocketMessageConsumer;
import com.myproj.app.candp.RocketMessageProducer;
import com.myproj.app.constant.RocketConstants;
import com.myproj.app.listener.RocketListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * RockeMQ Producer and Consumer
 *
 */
@Configuration
public class RocketConfig
{
    @Value("${rocket.name_server.address:47.99.112.38:9876}")
    private String nameServerAddr;

    @Value("${rocket.producer.group:Group}")
    private String producerGroup;

    @Value("${rocket.producer.timeout:10000}")
    private int producerTimeout;

    @Value("${rocket.consumer.group:Group}")
    private String consumerGroup;

    @Bean
    public RocketMessageProducer rocketProducer(){
        RocketMessageProducer producer = new RocketMessageProducer(nameServerAddr,producerGroup,producerTimeout);
        return producer;
    }

    /**
     * 监听
     *
     * @return
     */
    @Bean("rocketConsumer")
    public RocketMessageConsumer rocketConsumer() {
        RocketMessageConsumer consumer = new RocketMessageConsumer( nameServerAddr,
                consumerGroup + "_ct", RocketConstants.TOPIC_ROCKET);
        consumer.registerMessageListener(rocketListener());
        return consumer;
    }
    @Bean
    public RocketListener rocketListener() {
        return new RocketListener();
    }

}
