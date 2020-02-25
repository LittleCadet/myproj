package com.myproj.app.service;

import com.alibaba.fastjson.JSONObject;
import com.myproj.app.entity.SysCompanyEntity;
import com.myproj.app.entity.SysCompanyEntityV2;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

/**
 * @author LittleCadet
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class RedisServiceImplTest {

    @Autowired
    private RedisServiceImpl redisService;

    private String key;

    private String value;

    @Before
    public void init(){
        key = UUID.randomUUID().toString();;
        value = UUID.randomUUID().toString();;
    }

    @Test
    public void setValue()throws InterruptedException
    {
        System.out.println("key:" + key + ",value:" + value);
        redisService.setValue(key,value);
        Assert.assertEquals(value,redisService.getValue(key));

    }

    @Test
    public void lock(){
        value = UUID.randomUUID().toString();
        key = key.replace("-","");
        value = value.replace("-","");

        redisService.processData(key,value);
    }

    /**
     * 证明了序列化和反序列化，不需要是同一个类，反序列化时，取相同属性即可
     */
    @Test
    public void set(){
        redisService.setValue(String.valueOf(10000168), JSONObject.toJSONString(SysCompanyEntity.builder().id(10000168).entWxPay(100).build()));
        Object value = redisService.getValue(String.valueOf(10000168));
        SysCompanyEntityV2 sysCompanyEntityV2 = JSONObject.parseObject(value.toString(), SysCompanyEntityV2.class);

        System.out.println("===============value整体:" + redisService.getValue(String.valueOf(10000168)));
        System.out.println("===============value:" + sysCompanyEntityV2.getEntWxPay());
    }

}
