package com.myproj.app.controller.message.producer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author LittleCadet
 */
@Slf4j
@Component
public class RedisConsumer {

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @Scheduled(fixedRate = 1000L,initialDelay = 1000L)
    public void consumer() {
        log.info("consumer :" + redisTemplate.opsForList().rightPop("a"));
        log.info("consumer :" + redisTemplate.opsForList().rightPop("b"));
        log.info("consumer :" + redisTemplate.opsForList().rightPop("c"));
    }
}
