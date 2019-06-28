package com.myproj.app.eventbus.controller;

import com.google.common.eventbus.EventBus;
import com.myproj.app.eventbus.entity.Message;
import com.myproj.app.eventbus.entity.Messages;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.Date;

/**
 * 发布类
 * @author LittleCadet
 */
@Slf4j
@RestController
@RequestMapping("/send")
public class SendMessageController {

    @Autowired
    private EventBus eventBus;

    /**
     * 发布者：观察者
     *
     * 1.eventBus异步模式要想取到返回值，需要用DeferredResult异步设置结果
     * 2.发布者的返回值必须设置为DeferredResult，如果直接设置为String，会导致有几率取不到返回值。
     */
    @GetMapping("sendMessage")
    public DeferredResult<String> sendMessage(){

        DeferredResult<String> outputMessage = new DeferredResult<String>(30000L);
        Message message = Message.builder()
                .user("和尚")
                .message("撩妹子")
                .date(new Date().getTime())
                .output(outputMessage)
                .build();

        DeferredResult<String> outputMessages = new DeferredResult<String>(30000L);
        Messages messages = Messages.builder()
                .user("住持")
                .message("棒打鸳鸯")
                .date(new Date().getTime())
                .output(outputMessages)
                .build();


        eventBus.post(message);

        System.out.println(Thread.currentThread().getName()+"========message:消息已发送===========");

        eventBus.post(messages);

        System.out.println(Thread.currentThread().getName()+"========messages:消息已发送===========");

        return outputMessages;
    }
}
