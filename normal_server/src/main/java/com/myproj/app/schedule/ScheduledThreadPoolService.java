package com.myproj.app.schedule;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author shenxie
 * @date 2020/10/26
 */
public class ScheduledThreadPoolService {

    private static ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor(new ThreadFactoryBuilder().setNameFormat("biz-%d").build());

    public static void main(String[] args) {
        scheduledExecutorService.scheduleWithFixedDelay(() -> {
            try {
                Thread.sleep(4000);
                System.out.println("1");
                //throw new Exception();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } , 0 , 2 , TimeUnit.SECONDS);
    }
}
