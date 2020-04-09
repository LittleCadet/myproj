package com.error.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author LittleCadet
 * @Date 2020/3/25
 */
@SpringBootApplication(scanBasePackages = "com.error.app")
public class ErrorServerApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(ErrorServerApplication.class,args);
    }
}
