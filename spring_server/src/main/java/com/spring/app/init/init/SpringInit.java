package com.spring.app.init.init;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * @author shenxie
 * @date 2020/10/26
 */
@Component
public class SpringInit implements ApplicationListener<ContextRefreshedEvent> {
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if(null == event.getApplicationContext().getParent()){
            System.out.println("SpringInit初始化成功");
        }
    }
}
