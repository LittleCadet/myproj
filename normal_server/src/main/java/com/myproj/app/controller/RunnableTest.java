package com.myproj.app.controller;

/**
 * @author shenxie
 * @date 2024/3/19
 */
public class RunnableTest implements Runnable{
    @Override
    public void run() {
        System.out.println("runnableTest执行");
    }
}
