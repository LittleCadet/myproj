package com.spring.app.publish.springpublish.publisher;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * 所有事件发布的父类
 * @author shenxie
 * @date 2020/5/21
 */
public abstract class AbstractMessagePublish implements ApplicationContextAware {

    protected ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }
}
