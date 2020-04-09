package com.localcache.app.test;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author LittleCadet
 * @Date 2020/4/9
 */
public class aa
{

    public static void main(String[] args)
    {
        ConcurrentHashMap<String,String> cache = new ConcurrentHashMap<>(3);
        cache.put("1","1");
        cache.put("2","1");
        cache.put("3","1");
        cache.put("4","1");
        cache.put("5","1");
        cache.put("6","3");
        cache.put("7","1");
        cache.put("8","1");
        cache.put("9","1");
        cache.put("10","1");
        cache.put("11","1");
        cache.put("12","1");
        cache.put("13","1");
        cache.put("14","1");
        cache.put("15","1");
        cache.put("16","1");
        cache.put("17","5");

        cache.forEach((k,v) -> System.out.println("k:v = " + k + ":" + v));
    }
}
