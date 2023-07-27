package com.spring.app.publish.selfpublish.listener;

import com.spring.app.publish.selfpublish.event.SelfMessageEvent;
import com.spring.app.publish.springpublish.event.MessageEvent;
import lombok.extern.slf4j.Slf4j;

/**
 * 事件监听1
 * @author shenxie
 * @date 2023/6/19
 */
@Slf4j
public class Message1Listener implements MessageListener{
    @Override
    public void action(SelfMessageEvent messageEvent) {
        log.info("自定义：消费 消息V1, 线程名:{}", Thread.currentThread().getName());
        System.out.println(messageEvent);
    }
}
