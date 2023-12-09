package com.myproj.app.threads;

import lombok.SneakyThrows;

class InteruptTest extends Thread {
    public static void main(String args[]) throws InterruptedException {
        InteruptTest thread = new InteruptTest();
        thread.start();
        Thread.sleep(3000);
        // 发出中断请求
        thread.interrupt();
    }

    @SneakyThrows
    public void run() {
        // 每隔一秒检测是否设置了中断标示
        while (!Thread.currentThread().isInterrupted()) {
            System.out.println("thread name:" + Thread.currentThread().getName() + "Thread is running...");
            long time = System.currentTimeMillis();
            // 使用while循环模拟 sleep
            while ((System.currentTimeMillis() - time < 1000) ) {
            }
        }

        Thread.sleep(100);
        System.out.println("thread name:" + Thread.currentThread().getName() + "Thread exiting under request...");
    }
}