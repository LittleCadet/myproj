package com.spring.app.publish.selfpublish;

import com.spring.app.publish.selfpublish.event.SelfMessageEvent;
import com.spring.app.publish.selfpublish.listener.Message1Listener;
import com.spring.app.publish.selfpublish.listener.Message2Listener;
import com.spring.app.publish.selfpublish.publish.AsyncExecutorSelfMessagePublish;
import com.spring.app.publish.selfpublish.publish.AsyncRunnableSelfMessagePublish;
import com.spring.app.publish.selfpublish.publish.SelfMessagePublish;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author shenxie
 * @date 2023/6/19
 */
@Slf4j
@RestController
public class MessagePublishFacade {

    @Autowired
    private SelfMessagePublish selfMessagePublish;

    @Autowired
    private AsyncRunnableSelfMessagePublish asyncRunnableSelfMessagePublish;

    @Autowired
    private AsyncExecutorSelfMessagePublish asyncExecutorSelfMessagePublish;

    @GetMapping("/self/publish")
    public Boolean selfPublish() {
        selfMessagePublish.addListener(new Message1Listener());
        selfMessagePublish.addListener(new Message2Listener());
        log.info("自定义：发送事件：开始");
        selfMessagePublish.publishMessageEvent(new SelfMessageEvent("自定义： 发送事件1", 100000));
        log.info("自定义：发送事件：结束");
        return true;
    }

    @GetMapping("/self/publish/async/runnable")
    public Boolean asyncSelfPublish() {
        asyncRunnableSelfMessagePublish.addListener(new Message1Listener());
        asyncRunnableSelfMessagePublish.addListener(new Message2Listener());
        log.info("自定义：异步发送事件：开始, 线程名:{}", Thread.currentThread().getName());
        new Thread(asyncRunnableSelfMessagePublish).start();
        log.info("自定义：异步发送事件：结束, 线程名:{}", Thread.currentThread().getName());
        return true;
    }

    @GetMapping("/self/publish/async/executors")
    public Boolean asyncExecutorSelfPublish() {
        asyncExecutorSelfMessagePublish.addListener(new Message1Listener());
        asyncExecutorSelfMessagePublish.addListener(new Message2Listener());
        log.info("自定义：异步发送事件：开始, 线程名:{}", Thread.currentThread().getName());
        asyncExecutorSelfMessagePublish.publishMessageEvent(new SelfMessageEvent("自定义： Executors异步发送事件1", 100000));
        log.info("自定义：异步发送事件：结束, 线程名:{}", Thread.currentThread().getName());
        return true;
    }
}
