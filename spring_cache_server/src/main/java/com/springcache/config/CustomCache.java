package com.springcache.config;

import com.alibaba.fastjson.JSONObject;
import com.springcache.constrant.CacheConstrant;
import com.springcache.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by yanghaiyang on 2019/12/2 12:00
 * 缓存工具 cacheManager
 * 解决部分更新及删除调用查询的多余开销及代码冗余
 * 缓存数据结构
 *  cacheName(缓存名称)
 *      key(数据key前缀{#id}) Object
 *      key(数据key前缀{#id}) Object
 *      key(数据key前缀{#id}) Object
 */
@Component
@Slf4j
public class CustomCache
{

    @Autowired
    private CacheManager cacheManager;


    /**
     * 一个对象保存到指定缓存中
     * @param key   key值
     * @param o 缓存的对象
     */
    public int cachePutObject(String key, Object o){
        log.info("添加缓存 Objec:{}",o);
        Cache cache = cacheManager.getCache(CacheConstrant.CACHE_NAME);
        cache.put(key,o);
        return 1;
    }

    /**
     *一个对象集合保存到指定缓存中
     *  注意保存的对象必须有id
     * @param keyPrefix key前缀
     * @param objects   保存的对象集合
     * @param fieldKey 缓存对象取值字段
     * @param
     */
    public int cachePutObjectList(String keyPrefix,String fieldKey, List objects){
        log.info("批量添加缓存 Objecs:{}",objects);
        int row = 0;
        for (Object t : objects){
            JSONObject jsonObject = JSONObject.parseObject(t.toString());
            String id = jsonObject.get(fieldKey).toString();
            this.cachePutObject(keyPrefix+id,t);
            ++row;
        }
        return row;
    }

    /**
     * 删除缓存  指定主键
     * @param key
     * @return
     */
    public int cacheEvictObject(String key){
        log.info("删除缓存 key:{}",key);
        Cache cache = cacheManager.getCache(CacheConstrant.CACHE_NAME);
        cache.evict(key);
        return 1;
    }

    /**
     * 批量删除缓存指定主键
     * @param keyPrefix 缓存前缀
     * @param objects   缓存对象
     * @param fieldKey 缓存对象取值字段
     * @return
     */
    public int cacheEvictObjectList(String keyPrefix,String fieldKey,List<User> objects){
        log.info("批量删除缓存 Objecs:{}",objects);
        int row = 0;
        for (Object t : objects){
            System.out.println(t.toString());
            JSONObject jsonObject = JSONObject.parseObject(t.toString());
            String id = jsonObject.get(fieldKey).toString();
            this.cacheEvictObject(keyPrefix+id);
            ++row;
        }
        return row;
    }

    public User cacheGetObject(String key){
        log.info("查询缓存 key:{}",key);
        Cache cache = cacheManager.getCache(CacheConstrant.CACHE_NAME);
        return (User)cache.get(key).get();
    }


}
