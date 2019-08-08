package com.myproj.app.controller.message.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * redis消息队列：生产者与消费者模型
 *
 * redis生产者与消费者只能保证一个人生产，让订阅的人谁先取，谁先消费。【只能消费一次，就会在消息队列中删除消息】
 * redis发布订阅模型，可以让一个人发布，多个人消费。
 *
 * @author LittleCadet
 */
@RestController("producer")
@RequestMapping("/redis")
public class RedisProducerController {

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @RequestMapping("/producer")
    public void producer(){
        redisTemplate.opsForList().leftPush("a","1");
        redisTemplate.opsForList().leftPush("b","2");
        redisTemplate.opsForList().leftPush("c","3");
    }
}
