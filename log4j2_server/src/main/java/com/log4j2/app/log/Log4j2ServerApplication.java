package com.log4j2.app.log;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author shenxie
 * @date 2022/12/29
 */
@SpringBootApplication
public class Log4j2ServerApplication {
    public static void main(String[] args) {
//        System.setProperty("Log4jContextSelector","org.apache.logging.log4j.core.async.AsyncLoggerContextSelector");
        SpringApplication.run(Log4j2ServerApplication.class, args);
    }
}
