package com.myproj.app.deadlock;

import com.google.common.collect.Maps;
import com.myproj.app.copy.Copy;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.units.qual.K;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author shenxie
 * @date 2020/9/25
 */
@Slf4j
@Service
public class Oom implements InitializingBean {


    @Override
    public void afterPropertiesSet() throws Exception {

//        Map<Integer, Integer> map = new HashMap(1 , 0.0000000001f);
        Map<Integer, Integer> map = new HashMap(10 , 10);




        new Thread(() -> {
            int i = 0;
            while(true){
                map.put((int)(Math.random()*Integer.MAX_VALUE) , (int)(Math.random()*Integer.MAX_VALUE) );
                log.info(String.format("第 【%s】 次防止" , i++));
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }
}
