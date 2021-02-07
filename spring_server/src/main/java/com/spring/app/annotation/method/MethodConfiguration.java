package com.spring.app.annotation.method;

import org.springframework.beans.factory.InitializingBean;

/**
 * @author shenxie
 * @date 2021/1/25
 */
public class MethodConfiguration implements InitializingBean {


    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println();
    }
}
