package com.spring.app.bean;

import org.springframework.beans.BeansException;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @author shenxie
 * @date 2022/5/19
 */
@Component
public class BeanPostProcessor implements org.springframework.beans.factory.config.BeanPostProcessor {

    @Nullable
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if(beanName.equals("beanInstance")){
            System.out.println("=======postProcessBeforeInitialization");
        }
        return bean;
    }

    @Nullable
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if(beanName.equals("beanInstance")){
            System.out.println("======postProcessAfterInitialization");
        }
        return bean;
    }

}
