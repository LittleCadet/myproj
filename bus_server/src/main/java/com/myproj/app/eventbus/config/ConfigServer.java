package com.myproj.app.eventbus.config;

import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;
import com.myproj.app.eventbus.service.SendMessageImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
