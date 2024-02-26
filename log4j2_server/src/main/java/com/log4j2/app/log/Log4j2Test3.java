package com.log4j2.app.log;

import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

//@Slf4j
public class Log4j2Test3{
    // 定义日志记录器对象
    public static final Logger LOGGER = LogManager.getLogger(Log4j2Test3.class);

    public static void main(String[] args) {
        LOGGER.fatal("fatal");
        LOGGER.error("error");
        LOGGER.warn("warn");
        LOGGER.info("info");
        LOGGER.debug("debug");
        LOGGER.trace("trace");
    }
}