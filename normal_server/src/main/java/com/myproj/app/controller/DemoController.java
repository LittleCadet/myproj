package com.myproj.app.controller;

import java.util.concurrent.CompletableFuture;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author shenxie
 * @date 2024/2/8
 */
@Slf4j
@RequestMapping("demo")
@RestController
public class DemoController {

//    @Autowired
//    private KafkaTemplate kafkaTemplate;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @GetMapping("test")
    public String showInfo(){
        completableFuture();
        redis();
//        kafkaSend();
        return "完成";
    }

    private void redis(){
        redisTemplate.opsForValue().set("test", "1");
        System.out.println("redis: " + redisTemplate.opsForValue().get("test"));;
    }

//    private void kafkaSend(){
//        kafkaTemplate.send("test-topic", "测试kafka trace");
//        System.out.println("kafka: 发送成功");
//    }
//
//    @KafkaListener(topics = {"test-topic"}, groupId = "kafkaTrace")
//    private void kafkaConsume(ConsumerRecord<String,String> consumerRecord){
//        System.out.println("kafka：消费成功：" + consumerRecord.value());
//    }

    private void completableFuture(){
        CompletableFuture.supplyAsync(() -> "hello")
                .thenApply(s -> {
                    s = s + "world";
                    System.out.println(s);
                    return s;
                })
                .thenApply(String :: toUpperCase)
                .thenCombine(CompletableFuture.completedFuture("JAVA") , (s1 , s2) -> s1 + s2)
                .thenAccept(System.out::println);
    }
}
