package com.spring.app.publish.selfpublish.publish;

import com.google.common.collect.Lists;
import com.spring.app.publish.selfpublish.event.SelfMessageEvent;
import com.spring.app.publish.selfpublish.listener.MessageListener;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 事件发布
 * @author shenxie
 * @date 2023/6/19
 */
@Service
public class SelfMessagePublish {
    private List<MessageListener> messageListeners = Lists.newArrayList();

    public void addListener(MessageListener messageListener) {
        messageListeners.add(messageListener);
    }

    public void publishMessageEvent(SelfMessageEvent messageEvent) {
        messageListeners.forEach(messageListener -> {
            messageListener.action(messageEvent);
        });
    }
}
