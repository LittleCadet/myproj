package com.myproj.controller;

import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import javax.annotation.PostConstruct;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author shenxie
 * @date 2025/11/24
 */
@RestController
@RequestMapping("test")
public class Controller2 {

    private OkHttpClient httpClient;

    @PostConstruct
    public void init() {

        httpClient = new OkHttpClient();
    }

    @GetMapping("rpc2")
    public String rpc() throws IOException {
        Request request = new Request.Builder()
                .get()
                .url("http:localhost:8083/test/rpc3")
                .build();

        Response response = httpClient.newCall(request).execute();
//        Response response2 = httpClient.newCall(request).execute();
//        Response response3 = httpClient.newCall(request).execute();
//        Response response4 = httpClient.newCall(request).execute();
        return "ok2";
    }

    @GetMapping("async")
    public String async() throws InterruptedException {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("runnable执行了");
            }
        }).start();
        new Thread(String.valueOf(new Callable() {
            @Override
            public Object call() throws Exception {
                System.out.println("callable执行了");
                return null;
            }
        })).start();

        new Thread(() -> {
            System.out.println("lambda runnable执行了");
        }).start();

        new Thread(String.valueOf((Callable) () -> {
            System.out.println("lambda runnable执行了");
            return null;
        })).start();

        new Thread(new TestRunnable()).start();
        new Thread(String.valueOf(new TestCallable())).start();


        Executors.newFixedThreadPool(1).execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("excutor: runnable执行了");
            }
        });

        Executors.newFixedThreadPool(1).submit(new Callable() {
            @Override
            public Object call() throws Exception {
                System.out.println("executor: callable执行了");
                return null;
            }
        });

        Executors.newFixedThreadPool(1).execute(new TestRunnable());
        Executors.newFixedThreadPool(1).submit(new TestCallable());


        TimeUnit.SECONDS.sleep(2);

        return "OK";
    }
}
