package com.myproj.app.controller.listener;

import com.myproj.app.constant.RedisListenerConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
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
