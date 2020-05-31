package com.dubbo.app;

import com.dubbo.app.provider.api.ProviderService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.support.ClassPathXmlApplicationContext;

//@SpringBootApplication
public class DubboServerApplication {

    public static void main(String[] args) {
        //SpringApplication.run(DubboServerApplication.class, args);

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:provider.xml", "classpath:consumer.xml");
        context.start();
        ProviderService providerService = (ProviderService) context.getBean("providerService");

        System.out.println(providerService.providerService());
    }

}
