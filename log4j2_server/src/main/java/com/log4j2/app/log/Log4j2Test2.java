//package com.log4j2.app.log;
//
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.springframework.beans.factory.InitializingBean;
//import org.springframework.stereotype.Service;
//
///**
// * @author shenxie
// * @date 2022/12/29
// */
//@Service
//public class Log4j2Test2 implements InitializingBean {
//    public static final Logger LOGGER = LogManager.getLogger(Log4j2Test2.class);
//    @Override
//    public void afterPropertiesSet() throws Exception {
//        // 日志消息输出
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
//}
