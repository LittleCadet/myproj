package com.myproj.app.controller.message.listener;

import com.myproj.app.constant.RedisListenerConstants;
import com.myproj.app.service.consumer.ProcessMessage;
import com.myproj.app.service.consumer.ProcessMessageV2;
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
public class RedisSubscirber {

    @Autowired
    private JedisConnectionFactory jedisConnectionFactory;

    /**
     * redis的消费者，需要随着容器的启动而启动，否则消费不到消息
     * @return
     */
    @Bean
    public RedisMessageListenerContainer consumer(){

        log.info("consumer enter");

        //设置redis消息的监听容器
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();

        container.setConnectionFactory(jedisConnectionFactory);

        container.addMessageListener(

                //定义任务执行器
                new MessageListenerAdapter(new ProcessMessage()),

                //定义topic
                new ChannelTopic(RedisListenerConstants.channel));

        container.addMessageListener(

                //定义任务执行器
                new MessageListenerAdapter(new ProcessMessageV2()),

                //定义topic
                new ChannelTopic(RedisListenerConstants.channel));

        log.info("consumer exit");

        return container;
    }

    @Bean
    public RedisMessageListenerContainer consumerV2(){

        log.info("consumerV2 enter");

        //设置redis消息的监听容器
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();

        container.setConnectionFactory(jedisConnectionFactory);


        container.addMessageListener(

                //定义任务执行器
                new MessageListenerAdapter(new ProcessMessage()),

                //定义topic
                new ChannelTopic(RedisListenerConstants.channel));

        container.addMessageListener(

                //定义任务执行器
                new MessageListenerAdapter(new ProcessMessageV2()),

                //定义topic
                new ChannelTopic(RedisListenerConstants.channel));

        log.info("consumerV2 exit");

        return container;
    }

}
