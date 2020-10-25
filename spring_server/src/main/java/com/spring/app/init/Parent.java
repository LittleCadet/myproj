package com.spring.app.init;

/**
 * @author shenxie
 * @date 2020/10/25
 */
public class Parent {

    // 静态变量
    public static String pStaticField = "父类--静态变量";

    // 静态初始化块
    static {
        System.out.println(pStaticField);
        System.out.println("父类--静态初始化块");
    }

    // 变量
    public String pField = "父类--变量";

    // 初始化块
    {
        System.out.println(pField);
        System.out.println("父类--初始化块");
    }

    // 构造器
    public Parent() {
        System.out.println("父类--构造器");
    }
    
    
    

}


