package com.spring.app.springpublish.publisher;

import com.spring.app.springpublish.event.MessageEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author shenxie
 * @date 2020/5/21
 */
@Slf4j
@Service
public class MessagePublish extends AbstractMessagePublish {

    /**
     * 事件发布
     * @param event
     */
    public void send(MessageEvent event){
        log.info("开始 事件发布！");
        context.publishEvent(event);
        log.info("结束 事件发布");
    }
}
