package com.myproj.app.clazz;

/**
 * @author shenxie
 * @date 2020/9/23
 */
public class ClazzDemo {

    /**
     * getClass获取当前的动态类
     * @param args
     */
    public static void main(String[] args) {
        AbstractA clazz = new AbstractA();

        System.out.println(clazz.getClass());
    }
}
