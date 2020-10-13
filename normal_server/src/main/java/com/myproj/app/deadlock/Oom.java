package com.myproj.app.deadlock;

import com.google.common.collect.Maps;
import com.myproj.app.copy.Copy;
import org.checkerframework.checker.units.qual.K;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author shenxie
 * @date 2020/9/25
 */
/*@Service*/
public class Oom implements InitializingBean {


    @Override
    public void afterPropertiesSet() throws Exception {
        int i = 0;

        Map<Integer, Integer> map = new HashMap(1 , 0.0000000001f);




        while(true){
            map.put((int)(Math.random()*Integer.MAX_VALUE) , (int)(Math.random()*Integer.MAX_VALUE) );
            System.out.println(String.format("第 【%s】 次防止" , i++));
        }
    }
}
