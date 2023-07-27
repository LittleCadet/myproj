package com.spring.app.publish.selfpublish.publish;

import com.google.common.collect.Lists;
import com.spring.app.publish.selfpublish.event.SelfMessageEvent;
import com.spring.app.publish.selfpublish.listener.MessageListener;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 事件发布： 广播中异步：
 * 广播异步 比 直接异步： 更优秀： 原因：
 * 1. 事件的监听器为N个， 此时用异步处理更好。  而并非同步。
 * 注意点：
 * 1. 消息的发送者 和 消费者： 均是同一个线程。
 * 2. 自定义事件发布订阅模型 与 springEvent的区别：
 *  a. 前者： 监听器 + 事件发布： 均可以不交由Spring管理。
 *  b. 后者： 监听器： 必须： 交由Spring管理。 因为： 只有这样， 才能扫描到 @EventListener的注解。
 *
 * @author shenxie
 * @date 2023/6/19
 */
@Slf4j
@Service
public class AsyncExecutorSelfMessagePublish {

    private List<MessageListener> messageListeners = Lists.newArrayList();

    public void addListener(MessageListener messageListener) {
        messageListeners.add(messageListener);
    }

    public void publishMessageEvent(SelfMessageEvent messageEvent) {
        messageListeners.forEach(messageListener -> {

            Executors.newSingleThreadScheduledExecutor()
                    .execute(() -> {
                        log.info("广播中：{} ", Thread.currentThread().getName());
                        messageListener.action(messageEvent);
                    });
        });
    }
}
