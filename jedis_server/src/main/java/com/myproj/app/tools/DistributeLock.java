package com.myproj.app.tools;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * 分布式锁
 *
 * @author LittleCadet
 */
@Slf4j
@Service
public class DistributeLock {
    private String symbol = ":";

    private String fixValue = "server";

    private String suffix = "@jedis";

    private Long timeOut = 30 * 1000L;

    private ThreadLocal<String> threadLocal = new ThreadLocal<>();

    private static final String UNLOCK_LUA = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";

    @Autowired
    private RedisTemplate<String, String> redis;


    /**
     * 基于AOP，上分布式锁:可重入锁 + 阻塞锁 + 非公平锁
     *
     * @param key
     * @param value
     */
    public Boolean lock(String key, String value) {


        Boolean flag = false;

        key = key.concat(symbol).concat(fixValue).concat(suffix);

        String thradLocalValue = threadLocal.get();

        //可重入锁 [简化版]
        if (null == thradLocalValue || !isReentrantLock(key, thradLocalValue)) {

            //阻塞锁
            do {

                flag = rawLock(key, value, timeOut);

                try {
                    //每隔50ms重试一次
                    if (!flag)
                        Thread.sleep(50);
                } catch (InterruptedException e) {
                    log.error("failed to sleep thread");
                }

            } while (!flag);

        }

        return true;
    }

    /**
     * 原始锁
     */
    public Boolean rawLock(String key, String value, Long expirTime) {

        log.info("rawLock enter,key:{},value:{}", key, value);

        //保证使用内部的redis命令用的是同一个池连接，从而避免死锁的发生
        return redis.execute(new SessionCallback<Boolean>() {

            @Override
            public Boolean execute(RedisOperations redisOperations) throws DataAccessException {

                redisOperations.multi();

                //TODO 注意：setIfAbsent中可以设置过期时间，是在springBoot版本2.1以后的改动。
                //TODO 注意：必须使用：redisTempalte的setIfAbsent ，或者redis的setnx，其他情况均会覆盖原值，造成释放锁出问题【2个线程，在容器最开始时，分布式锁没有人去占用，所以get不到锁，于是2个线程会进入execute（），但是A线程比B线程优先抢占到了锁，但是B线程依旧可以通过set方法将锁覆盖，这样A在释放锁时，会因为对应的value不正确，而导致释放锁异常】
                redis.opsForValue().setIfAbsent(key, value, expirTime, TimeUnit.MILLISECONDS);

                //TODO 注意：redis过期时间需要少于应用服务器平均启动时间，这样，即使此时服务器挂了，threadLocal中没有值，那么应用服务器启动时间已经超过redis的过期时间，此时锁已经释放
                threadLocal.set(value);

                redisOperations.exec();

                log.info("succeed to get distributed lock,key:{},value:{}", key, value);

                return true;
            }
        });

    }

    /**
     * 可重入锁
     *
     * @param key
     * @param threadLocalValue
     * @return
     */
    public Boolean isReentrantLock(String key, String threadLocalValue) {

        String value = redis.opsForValue().get(key);

        Boolean flag = threadLocalValue.equals(value);

        if (flag) {
            log.info("it is reetrantLock");
        } else {
            log.info("it not reentrantLock");
        }
        return flag;
    }

    /**
     * 释放分布式锁：lua脚本
     * <p>
     * 备注：lua脚本用value去删除key【因为需要确保解锁的线程的就是加锁的线程】，所以value必须全局唯一
     *
     * @param key
     */
    public void unlock(String key, String value) {

        key = key.concat(symbol).concat(fixValue).concat(suffix);

        //准备lua脚本
        DefaultRedisScript<Boolean> script = new DefaultRedisScript<>(UNLOCK_LUA, Boolean.class);

        Boolean flag = redis.execute(script, Arrays.asList(key), value);


        if (flag) {

            //移除可重入锁
            threadLocal.remove();

            log.info("succeed to release distributed lock");
        } else {
            log.info("failed to release distributed lock");
        }

    }
}
