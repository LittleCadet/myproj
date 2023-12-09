package com.myproj.app.future;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author shenxie
 * @date 2023/12/1
 */
public class FutureDemoV3 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Future<?> future = Executors.newFixedThreadPool(1).submit(() -> {
            System.out.println("执行完成");
        });

        future.get();
        future.isDone();
    }
}
