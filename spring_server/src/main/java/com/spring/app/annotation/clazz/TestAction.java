package com.spring.app.annotation.clazz;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author shenxie
 * @date 2021/4/21
 */
@Service
public class TestAction implements InitializingBean {

    @Resource
    private TestConfiguration testConfiguration;

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("=================testAction:开始：" + testConfiguration);
    }
}
