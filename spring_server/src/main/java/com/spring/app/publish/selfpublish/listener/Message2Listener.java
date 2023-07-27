package com.spring.app.publish.selfpublish.listener;

import com.spring.app.publish.selfpublish.event.SelfMessageEvent;
import lombok.extern.slf4j.Slf4j;

/**
 * 事件监听2
 * @author shenxie
 * @date 2023/6/19
 */
@Slf4j
public class Message2Listener implements MessageListener{
    @Override
    public void action(SelfMessageEvent messageEvent) {
        log.info("自定义：消费 消息V2, 线程名:{}", Thread.currentThread().getName());
        System.out.println(messageEvent);
    }
}
