package com.springcache.service;

import com.springcache.config.CustomCache;
import com.springcache.constrant.CacheConstrant;
import com.springcache.entity.User;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author LittleCadet
 * @Date 2020/2/9
 */
@Service
@CacheConfig(cacheNames = CacheConstrant.CACHE_NAME)
public class CacheService
{
    @Resource
    private CustomCache customCache;

    @CachePut(keyGenerator = "cacheKeyGenerator" ,unless = "#result=null")
    public User putUser(User user){
        return user;
    }

    @Cacheable(keyGenerator = "cacheKeyGenerator",unless = "#result=null")
    public User getUser(User user){
        return User.builder().name("无缓存").build();
    }

    public void save(User user){
        customCache.cachePutObject(CacheConstrant.CACHE_SERVICE + user.getId(),user);
    }

    public User get(User user){
        return customCache.cacheGetObject(CacheConstrant.CACHE_SERVICE + user.getId());
    }

    public void evict(User user){
        customCache.cacheEvictObject(CacheConstrant.CACHE_SERVICE + user.getId());
    }

    @CacheEvict(allEntries = true,beforeInvocation = true)
    public void evictAll(User user){
        //customCache.cachePutObject(CacheConstrant.CACHE_SERVICE + user.getId(),user);
        System.out.println("缓存已清空");
    }
}
