package com.springcache.config;

import com.alibaba.fastjson.JSONObject;
import com.springcache.constrant.CacheConstrant;
import com.springcache.entity.User;
import com.springcache.service.CacheService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.time.Duration;

/**
 * @Author LittleCadet
 * @Date 2020/2/9
 */
@Slf4j
@EnableCaching //开启缓存
@Component
@SpringBootConfiguration
public class CacheKeyGenerator implements KeyGenerator
{

    @SneakyThrows
    @Override
    public Object generate(Object o, Method method, Object... objects)
    {
        log.info("o:{},method:{},object:{}",o,method,objects);
        String keyPrefix = null;
        String keySuffix = null;
        StringBuilder key = new StringBuilder();
        //key默认为0
        if (objects.length == 0)
        {
            return key.append(0);
        }

        if (o instanceof CacheService)
        {
            keyPrefix = CacheConstrant.CACHE_SERVICE;
            key.append(keyPrefix);
        }

        if (objects[0] instanceof User)
        {
            keySuffix = ((User)objects[0]).getId();
        }else{
            //参数对象转换为json 获取主键字段数据
            JSONObject jsonObject = JSONObject.parseObject(objects[0].toString());
            keySuffix = jsonObject.get("id") == null ? null : jsonObject.get("id").toString();
        }

        if (keySuffix == null) {
            throw new Exception( "redis cache error，not found key!");
        }else {
            key.append(keySuffix);
        }
        log.info("key:{}",key);
        return key;
    }

    @Bean
    public RedisCacheManager cacheManager(JedisConnectionFactory jedisConnectionFactory)
    {
        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig()
            .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(RedisSerializer.string()))
            .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(RedisSerializer.json()))
            //永久缓存
            .entryTtl(Duration.ZERO)
            //不允许缓存null 【默认允许】
            .disableCachingNullValues();

        RedisCacheManager redisCacheManager = RedisCacheManager
            .builder(jedisConnectionFactory)
            .cacheDefaults(config)
            .build();

        log.info("用jedis_server中的jedisConnectionFactory,初始化redisCacheManager完成");
        return redisCacheManager;
    }
}
