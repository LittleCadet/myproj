package com.myproj;

import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


@Slf4j
public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);
    
    public static void main(String[] args) {
        // 输出不同级别的日志信息
        logger.trace("Trace level log message");
        logger.debug("Debug level log message");
        logger.info("Info level log message");
        logger.warn("Warn level log message");
        logger.error("Error level log message");

        log.trace("Trace level log message");
        log.debug("Debug level log message");
        log.info("Info level log message");
        log.warn("Warn level log message");
        log.error("Error level log message");
        
        System.out.println("Hello World!");
    }
}