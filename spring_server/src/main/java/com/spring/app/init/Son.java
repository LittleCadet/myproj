package com.spring.app.init;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

/**
 * @author shenxie
 * @date 2020/10/25
 */
public class Son extends Parent  {

    // 静态变量
    public static String sStaticField = "子类--静态变量";

    // 静态初始化块
    static {
        System.out.println(sStaticField);
        System.out.println("子类--静态初始化块");
    }

    // 变量
    public String sField = "子类--变量";

    // 初始化块
    {
        System.out.println(sField);
        System.out.println("子类--初始化块");
    }

    // 构造器
    public Son() {
        System.out.println("子类--构造器");
    }

}

