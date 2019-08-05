package com.myproj.app.controller.distributeLock;

import com.myproj.app.service.RedisServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * redis测试aop
 * @author LittleCadet
 */
@RestController("redis")
@RequestMapping("/redis")
public class RedisServiceController {

    @Autowired
    private RedisServiceImpl redisService;

    @GetMapping("lock")
    public void lock() {
        String key = UUID.randomUUID().toString();
        String value = UUID.randomUUID().toString();
        redisService.processData(key,value);
    }
}
