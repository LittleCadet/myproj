package com.spring.app.init;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

/**
 * @author shenxie
 * @date 2020/10/25
 */
@Service
public class TestDemo implements InitializingBean {

    /**
     * 初始化顺序：
     *
     * 父类--静态变量
     * 父类--静态初始化块
     * 子类--静态变量
     * 子类--静态初始化块
     * 父类--变量
     * 父类--初始化块
     * 父类--构造器
     * 子类--变量
     * 子类--初始化块
     * 子类--构造器
     *
     */
    @Override
    public void afterPropertiesSet() {
        new Son();
    }
}
