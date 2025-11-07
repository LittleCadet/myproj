package com.myproj.app.controller;

import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author shenxie
 * @date 2024/3/19
 */
@RestController
public class DemoControllerV2 {

    @GetMapping("/demo/async")
    public String async(){
        new Thread(() -> {
            System.out.println("runnable执行");
        }).start();

        new RunnableTest().run();
//
//        Executors.newFixedThreadPool(1).submit(() -> {
//            System.out.println("runnable - executors执行");
//        });
//
//        Executors.newFixedThreadPool(1).submit(new Callable<String>() {
//            @Override
//            public String call() throws Exception {
//                System.out.println("callable - executors执行");
//                return "ok";
//            }
//        });
//
//        try {
//            TimeUnit.SECONDS.sleep(5);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }

        return "ok";
    }
}
