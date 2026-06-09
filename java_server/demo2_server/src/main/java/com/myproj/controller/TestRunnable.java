package com.myproj.controller;

/**
 * @author shenxie
 * @date 2026/1/21
 */
public class TestRunnable implements Runnable {
    @Override
    public void run() {
        System.out.println("继承runnable执行了");
    }
}
