package com.myproj.app.controller.listener;

import com.myproj.app.constant.RedisListenerConstants;
import com.myproj.app.service.consumer.ProcessMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.stereotype.Component;

/**
 * redis 监听：消费者
 * @author LittleCadet
 */
@Slf4j
@Component
public class RedisConsumer {

    @Autowired
    private JedisConnectionFactory jedisConnectionFactory;

    /**
     * redis的消费者，需要随着容器的启动而启动，否则消费不到消息
     * @return
     */
    @Bean
    public RedisMessageListenerContainer consumer(){

        //设置redis消息的监听容器
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();

        container.setConnectionFactory(jedisConnectionFactory);

        //设置redis的消费消息的topic + 消息处理类

        container.addMessageListener(new MessageListenerAdapter(this.processMessageContainer()),new ChannelTopic(RedisListenerConstants.channel));

        return container;
    }

    @Bean
    public ProcessMessage processMessageContainer(){
        return new ProcessMessage();
    }


}
