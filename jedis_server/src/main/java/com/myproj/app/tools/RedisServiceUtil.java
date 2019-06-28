package com.myproj.app.tools;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import java.util.concurrent.TimeUnit;

/**
 * redis工具类
 * @author LittleCadet
 */
@Slf4j
@Service
public class RedisServiceUtil {

    private String symbol = ":";

    private String fixValue = "server";

    private String suffix = "@jedis";

    @Autowired
    private RedisTemplate<String, String> redis;

    /**
     * 放值
     *
     * @param key
     * @param value
     * @return
     */
    public void setValue(String key, String value) {

        key = key.concat(symbol).concat(fixValue).concat(suffix);

        redis.opsForValue().set(key, value, 5000L, TimeUnit.MILLISECONDS);

    }

    /**
     * 取值
     *
     * @param key
     * @return
     */
    public String getValue(String key) {

        key = key.concat(symbol).concat(fixValue).concat(suffix);

        return redis.opsForValue().get(key);
    }

}
