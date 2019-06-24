package com.mypro.app.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author The flow developers
 */
@Slf4j
@Service
public class RedisServiceImpl {

    private String symbol = ":";

    private String fixValue = "server";

    private String suffix = "@jedis";

    @Autowired
    private RedisTemplate<String,String> redis;

    public Object setValue(String key,String value){

        key = key.concat(symbol).concat(fixValue).concat(suffix);

        redis.opsForValue().set(key,value,5000L, TimeUnit.MILLISECONDS);

        return redis.opsForValue().get(key);
    }

}
