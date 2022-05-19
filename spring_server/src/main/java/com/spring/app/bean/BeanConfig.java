package com.spring.app.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author shenxie
 * @date 2022/5/19
 */
@Configuration
public class BeanConfig {
    @Bean(initMethod = "init" , destroyMethod = "destroy")
    public BeanInstance beanInstance(){
        BeanInstance beanInstance = new BeanInstance();
        beanInstance.setOrderNo("====setter method");
        return beanInstance;
    }
}
