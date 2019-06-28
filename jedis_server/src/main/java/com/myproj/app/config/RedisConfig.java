package com.myproj.app.config;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

import java.time.Duration;

/**
 * redis配置
 * @author LittleCadet
 */
@Slf4j
@Configuration
public class RedisConfig extends CachingConfigurerSupport {

    @Value("${spring.redis.host:47.99.112.38}")
    private String host;

    @Value("${spring.redis.port:6379}")
    private Integer port;

    @Value("${spring.redis.password:cmVkaXNPZkVkd2FyZFNoZW4=}")
    private String password;

    //spring.redis.timeout = 0时，代表使用默认的超时时间：2000ms
    @Value("${spring.redis.timeout:0}")
    private Integer timeout;

    @Value("${spring.redis.database:0}")
    private Integer database;

    //连接池的最大连接数
    @Value("${spring.redis.pool.max-size:8}")
    private Integer poolMaxSize;

    //连接池的最大等待时间:-1：代表没有超时时间
    @Value("${spring.redis.pool.max-wait:-1}")
    private Integer poolMaxWait;

    //连接池中最大的空闲连接
    @Value("${spring.redis.pool.max-idle:8}")
    private Integer poolMaxIdle;

    @Value("${spring.redis.pool.min-idle:0}")
    private Integer poolMinIdle;

    /*@Autowired
    private JedisConnectionFactory jedisConnectionFactory;*/


    /**
     * 初始化：jedisConnectionFactory
     * @return
     */
    @Bean
    public JedisConnectionFactory jedisConnectionFactory(){
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration(host,port);
        redisStandaloneConfiguration.setDatabase(database);
        redisStandaloneConfiguration.setPassword(RedisPassword.of(password));

        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        System.out.println("poolMaxIdle:" + poolMaxIdle);
        jedisPoolConfig.setMaxIdle(poolMaxIdle);
        jedisPoolConfig.setMaxTotal(poolMaxSize);
        jedisPoolConfig.setMaxWaitMillis(poolMaxWait);
        jedisPoolConfig.setMinIdle(poolMinIdle);

        JedisClientConfiguration jedisClientConfiguration = JedisClientConfiguration.builder()
                .connectTimeout(Duration.ofMillis(timeout))
                .readTimeout(Duration.ofMillis(timeout))
                .usePooling()
                .poolConfig(jedisPoolConfig)
                .build();

        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(redisStandaloneConfiguration,jedisClientConfiguration);

        return jedisConnectionFactory;

    }

    /**
     * 初始化redisTempalte
     * @return
     */
    @Bean
    public RedisTemplate<String,Object> redisTemplate(){

        log.info("===============init to create redisTemplate==============");

        RedisTemplate<String,Object> redisTemplate = new RedisTemplate<>();

        RedisSerializer fastJsonSerializer = new RedisSerializer(){

            @Override
            public byte[] serialize(Object o) throws SerializationException {
                return JSON.toJSONBytes(o);
            }

            @Override
            public Object deserialize(byte[] bytes) throws SerializationException {
                return JSON.toJSONString(bytes);
            }
        };


        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(fastJsonSerializer);

        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(fastJsonSerializer);

        redisTemplate.setConnectionFactory(jedisConnectionFactory());

        log.info("===============successed to create redisTemplate==============");

        return redisTemplate;

    }

    @Override
    @Bean
    public CacheManager cacheManager() {
        // 设置缓存有效期
        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofHours(23));

        return RedisCacheManager
                .builder(RedisCacheWriter.nonLockingRedisCacheWriter(jedisConnectionFactory()))
                .cacheDefaults(redisCacheConfiguration).build();
    }

}
