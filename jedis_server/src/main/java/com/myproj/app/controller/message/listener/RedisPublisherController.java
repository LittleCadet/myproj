package com.myproj.app.controller.message.listener;

import com.myproj.app.constant.RedisListenerConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * redis消息队列：发布订阅模型
 *
 * redis生产者与消费者只能保证一个人生产，让订阅的人谁先取，谁先消费。【只能消费一次，就会在消息队列中删除消息】
 * redis发布订阅模型，可以让一个人发布，多个人消费。
 *
 * redis 监听：发布者
 * @author LittleCadet
 */
@Slf4j
@RestController("listener")
@RequestMapping("/redis")
public class RedisPublisherController {

    @Autowired
    private RedisTemplate redisTemplate;

    private String msg = "send one message";

    @RequestMapping("/publish")
    public void publish(){

        redisTemplate.convertAndSend(RedisListenerConstants.channel,msg);
    }
}
