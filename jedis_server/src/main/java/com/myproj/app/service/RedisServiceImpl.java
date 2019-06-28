package com.myproj.app.service;

import com.myproj.app.tools.DistributeLock;
import com.myproj.app.tools.RedisServiceUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Null;


/**
 * redis逻辑类
 * @author LittleCadet
 */
@Slf4j
@Service
public class RedisServiceImpl {


    @Autowired
    private RedisServiceUtil redis;

    @Autowired
    private DistributeLock distributeLock;

    /**
     * 放值
     */
    public void setValue(String key,String value){
        redis.setValue(key,value);
    }

    /**
     * 取值
     * @param key
     * @return
     */
    public Object getValue(String key){
        return redis.getValue(key);
    }




    /**
     * 分布式锁的应用
     *
     * @param key
     * @param value
     */
    public void processData(String key,String value){

        int sum = 0;

        Long start = System.currentTimeMillis();

        if(!distributeLock.lock(key,value)){

            return;

        }

        try{

            for(int i = 0 ; i<100 ;i++){

                sum += i;

            }

            System.out.println("sum:" + sum);

        }catch (Exception e){

            log.info("failed to calculate,e:{}",e.getMessage());
        }
        finally {
            distributeLock.unlock(key,value);

            Long end = System.currentTimeMillis();

            Long cost = end - start ;

            log.info("cost time : cost:{}",cost);
        }

    }


}
