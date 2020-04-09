package com.localcache.app.test;

import com.googlecode.concurrentlinkedhashmap.ConcurrentLinkedHashMap;
import com.googlecode.concurrentlinkedhashmap.Weighers;
import com.localcache.app.service.LocalCache;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author LittleCadet
 * @Date 2020/4/9
 */
@Slf4j
public class LruTest
{

    /**
     * ConcurrentLinkedHashMap
     * 优点：支持lru过期策略：但是需要在放入第一个超限的值之前，get一次，否则，lru不行
     * 缺点：
     * lru不完美
     * 不支持动态扩容，一旦设置了最大容量，那么超过最大容量时，会将一开始设置的值去除
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args)
        throws InterruptedException
    {
        LocalCache<String,String> localCache = new LocalCache<>();
        localCache.put("1","1");
        localCache.put("2","2");
        localCache.put("3","3");
        localCache.put("4","4");
        localCache.put("5","5");

        System.out.println(localCache.get("1"));

        Thread.sleep(4000);
        System.out.println("过期了 ： " + localCache.get("1"));


    }

}
