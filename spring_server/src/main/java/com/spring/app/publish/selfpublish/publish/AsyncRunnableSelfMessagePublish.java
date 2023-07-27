package com.spring.app.publish.selfpublish.publish;

import com.google.common.collect.Lists;
import com.spring.app.publish.selfpublish.event.SelfMessageEvent;
import com.spring.app.publish.selfpublish.listener.MessageListener;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 事件发布： 直接异步： 【即为入口异步】
 * @author shenxie
 * @date 2023/6/19
 */
@Slf4j
@Service
public class AsyncRunnableSelfMessagePublish implements Runnable{

    private List<MessageListener> messageListeners = Lists.newArrayList();

    public void addListener(MessageListener messageListener) {
        messageListeners.add(messageListener);
    }

    private void publishMessageEvent(SelfMessageEvent messageEvent) {
        messageListeners.forEach(messageListener -> {
            log.info("广播中：{} ", Thread.currentThread().getName());
            messageListener.action(messageEvent);
        });
    }

    @SneakyThrows
    @Override
    public void run() {
        TimeUnit.SECONDS.sleep(3);
        log.info("sendMessage前：{}" , Thread.currentThread().getName());
        sendMessage();
        log.info("sendMessage后：{}" , Thread.currentThread().getName());
    }


    private void sendMessage() {
        publishMessageEvent(new SelfMessageEvent("自定义： runnable异步发送事件1", 100000));
    }
}
