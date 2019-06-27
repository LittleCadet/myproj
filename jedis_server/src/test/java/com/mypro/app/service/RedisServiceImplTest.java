package com.mypro.app.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

/**
 * @author The flow developers
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
        key = "firstTest";
        value = "succeed";
    }

    @Test
    public void setValue(){

        redisService.setValue(key,value);

        Assert.assertEquals(value,redisService.getValue(key));

    }

    @Test
    public void lock(){
        value = UUID.randomUUID().toString();
        value = value.replace("-","");
        redisService.processData(key,value);
    }

}
