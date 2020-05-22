package com.myproj.app.eventbus.config;

import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.myproj.app.eventbus.service.SendMessageImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.concurrent.*;

/**
 * @author LittleCadet
 */
@Slf4j
@Component
public class ConfigServer {

    @Autowired
    private ExecutorService executorService;

    @Autowired
    private EventBus eventBus;


    @Bean
    public EventBus eventBus(){

        eventBus = new AsyncEventBus("eventBus",executorService);

        System.out.println("==============successed to init eventBus===============");
        return eventBus;
    }

    @Bean
    public ExecutorService threadPool(){

        //定义队列
        BlockingQueue<Runnable> queue = new LinkedBlockingQueue<>();

        //定义线程名
        ThreadFactory factory = new ThreadFactoryBuilder().setNameFormat("bus-worker-%d").build();

        //定义拒接策略
        RejectedExecutionHandler handler = new ThreadPoolExecutor.AbortPolicy();

        executorService = new ThreadPoolExecutor(16,32,60, TimeUnit.SECONDS,queue,factory,handler);

        executorService = Executors.newFixedThreadPool(20);

        System.out.println("==============successed to init threadPool=============");

        return executorService;
    }

    @Bean
    public SendMessageImpl sendMessageImpl(){

        SendMessageImpl sendMessageImpl = new SendMessageImpl();
        eventBus.register(sendMessageImpl);

        System.out.println("===========successed to register ============");

        return sendMessageImpl;
    }
}
