//package com.log4j2.app.log;
//
//import lombok.extern.slf4j.Slf4j;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.junit.Test;
//
//@Slf4j
//public class Log4j2Test3{
//    // 定义日志记录器对象
//    public static final Logger LOGGER = LogManager.getLogger(Log4j2Test3.class);
//    @Test
//    public void testQuick() throws Exception {
//        // 日志消息输出
//        LOGGER.fatal("fatal");
//        LOGGER.error("error");
//        LOGGER.warn("warn");
//        LOGGER.info("info");
//        LOGGER.debug("debug");
//        LOGGER.trace("trace");
//
//        if((int)(Math.random() * 10) % 2 == 0 ) {
//            throw new Exception();
//        }
//    }
//
//    @Test
//    public void testQuick2() throws Exception {
//        // 日志消息输出
//        log.error("error");
//        log.warn("warn");
//        log.info("info");
//        log.debug("debug");
//        log.trace("trace");
//
//        if((int)(Math.random() * 10) % 2 == 0 ) {
//            throw new Exception();
//        }
//    }
//}