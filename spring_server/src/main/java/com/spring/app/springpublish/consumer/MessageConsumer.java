package com.spring.app.springpublish.consumer;

import com.spring.app.springpublish.event.MessageEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author shenxie
 * @date 2020/5/21
 */
@Slf4j
@Service
public class MessageConsumer extends AbstractMessageConsumer {

    /**
     * 消费者：不管消费者是mq，还是eventBus，还是spring的发布订阅，消费者，都是需要随着容器的启动而启动，否则消费消息的类不能注册到上下文中
     *
     * @EventListener：spring消费者实现注册
     *
     * @param event
     */
    @EventListener
    public void onApplicationEventV1(MessageEvent event){
        log.info("消费 消息V1");
        System.out.println(event);
    }

    @EventListener
    public void onApplicationEventV2(MessageEvent event){
        log.info("消费 消息V2");
        System.out.println(event);
    }



}
