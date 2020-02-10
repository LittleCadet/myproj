package com.springcache.springcache;

import com.springcache.entity.User;
import com.springcache.service.CacheService;
import org.assertj.core.util.Lists;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @Author LittleCadet
 * @Date 2020/2/9
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CacheServiceTest
{
    @Autowired
    private CacheService cacheService;

    @Test
    public void set()
        throws InterruptedException
    {
        User user = User.builder().age("18").id("10000168").name("电脑小子").build();
        cacheService.putUser(user);

        System.out.println("user:" + cacheService.getUser(user));
        /*Thread.sleep(30*1000L);
        System.out.println("user:" + cacheService.getUser(user));*/
        Assert.assertEquals(user, cacheService.getUser(user));
    }

    @Test
    public void setV2()
        throws InterruptedException
    {
        User user = User.builder().age("18").id("10000176").name("电脑小子").build();
        cacheService.save(user);

        System.out.println("user:" + cacheService.get(user));
        /*Thread.sleep(30*1000L);
        System.out.println("user:" + cacheService.get(user));*/
        Assert.assertEquals(user, cacheService.get(user));
    }

    @Test
    public void evict()
    {
        User user1 = User.builder().age("18").id("10000170").name("电脑小子").build();
        User user2 = User.builder().age("18").id("10000169").name("电脑小子").build();
        User user3 = User.builder().age("18").id("10000168").name("电脑小子").build();
        cacheService.evict(user1);
        cacheService.evict(user2);
        cacheService.evict(user3);
    }

    @Test
    public void evictAll()
    {
        cacheService.evictAll(User.builder().age("18").id("10000170").name("电脑小子").build());
    }
}
