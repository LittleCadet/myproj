package com.myproj.app.config;

import com.myproj.app.candp.RocketMessageConsumer;
import com.myproj.app.candp.RocketMessageProducer;
import com.myproj.app.constant.RocketConstants;
import com.myproj.app.listener.RocketConcurrentlyListener;
import com.myproj.app.listener.RocketOrderlyListener;
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

    /**
     * 一般来说，生产者和消费者不在同一个模块，也就是说，一个模块只需要有生产者或者消费者的代码即可
     * @return
     */
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
    @Bean
    public RocketMessageConsumer rocketConsumer() {
        RocketMessageConsumer consumer = new RocketMessageConsumer( nameServerAddr,
                consumerGroup + "_ct", RocketConstants.TOPIC_ROCKET);

        //注册消息监听器
        //consumer.registerMessageListener(rocketConcurrentlyListener());
        consumer.registerMessageListener(rocketOrderlyListener());
        return consumer;
    }
    @Bean
    public RocketConcurrentlyListener rocketConcurrentlyListener() {
        return new RocketConcurrentlyListener();
    }

    @Bean
    public RocketOrderlyListener rocketOrderlyListener(){
        return new RocketOrderlyListener();
    }

}
