package com.myproj.app.eventbus.controller;

import com.google.common.eventbus.EventBus;
import com.myproj.app.eventbus.entity.Message;
import com.myproj.app.eventbus.entity.Messages;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author The flow developers
 */
@Slf4j
@RestController
@RequestMapping("/send")
public class SendMessageController {

    @Autowired
    private EventBus eventBus;

    /**
     * 发布者：观察者
     */
    @GetMapping("sendMessage")
    public void sendMessage(){
        Message message = Message.builder()
                .user("和尚")
                .message("撩妹子")
                .date(new Date().getTime())
                .build();

        Messages messages = Messages.builder()
                .user("住持")
                .message("棒打鸳鸯")
                .date(new Date().getTime())
                .build();


        eventBus.post(message);

        System.out.println(Thread.currentThread().getName()+"========message:消息已发送===========");

        eventBus.post(messages);

        System.out.println(Thread.currentThread().getName()+"========messages:消息已发送===========");
    }
}
