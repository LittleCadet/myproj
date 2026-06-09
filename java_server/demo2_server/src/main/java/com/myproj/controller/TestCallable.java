package com.myproj.controller;

import java.util.concurrent.Callable;

/**
 * @author shenxie
 * @date 2026/1/21
 */
public class TestCallable implements Callable {
    @Override
    public Object call() throws Exception {
        System.out.println("继承callable执行了");
        return null;
    }
}
