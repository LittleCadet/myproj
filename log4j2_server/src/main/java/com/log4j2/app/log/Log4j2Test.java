package com.log4j2.app.log;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import java.util.concurrent.Executors;

/**
 * @author shenxie
 * @date 2022/12/29
 */
@Slf4j
@Service
public class Log4j2Test implements InitializingBean {
    @Override
    public void afterPropertiesSet() throws Exception {
        // 日志消息输出
        log.error("error");
        log.warn("warn");
        log.info("info");
        log.debug("debug");
        log.trace("trace");


//        new Thread(() -> {
//            while(true) {
//                for (int i = 0; i < 100; i++) {
//                    int finalI = i;
//                    Executors.newFixedThreadPool(100).submit(() -> {
//                        log.info("log4j2的tps：在多线程异步的环境中：可以达到1800w, 是logback的10倍！！！{}", finalI);
//                        log.info("log4j2的tps：在多线程异步的环境中：可以达到1800w, 是logback的10倍！！！{}", finalI);
//                        log.info("log4j2的tps：在多线程异步的环境中：可以达到1800w, 是logback的10倍！！！{}", finalI);
//                        log.info("log4j2的tps：在多线程异步的环境中：可以达到1800w, 是logback的10倍！！！{}", finalI);
//                        log.info("log4j2的tps：在多线程异步的环境中：可以达到1800w, 是logback的10倍！！！{}", finalI);
//                        log.info("log4j2的tps：在多线程异步的环境中：可以达到1800w, 是logback的10倍！！！{}", finalI);
//                        log.info("log4j2的tps：在多线程异步的环境中：可以达到1800w, 是logback的10倍！！！{}", finalI);
//                        log.info("log4j2的tps：在多线程异步的环境中：可以达到1800w, 是logback的10倍！！！{}", finalI);
//                        log.info("log4j2的tps：在多线程异步的环境中：可以达到1800w, 是logback的10倍！！！{}", finalI);
//                        log.info("log4j2的tps：在多线程异步的环境中：可以达到1800w, 是logback的10倍！！！{}", finalI);
//                        log.info("log4j2的tps：在多线程异步的环境中：可以达到1800w, 是logback的10倍！！！{}", finalI);
//                        log.info("log4j2的tps：在多线程异步的环境中：可以达到1800w, 是logback的10倍！！！{}", finalI);
//                        log.info("log4j2的tps：在多线程异步的环境中：可以达到1800w, 是logback的10倍！！！{}", finalI);
//                        log.info("log4j2的tps：在多线程异步的环境中：可以达到1800w, 是logback的10倍！！！{}", finalI);
//                        log.info("log4j2的tps：在多线程异步的环境中：可以达到1800w, 是logback的10倍！！！{}", finalI);
//                        log.info("log4j2的tps：在多线程异步的环境中：可以达到1800w, 是logback的10倍！！！{}", finalI);
//                        log.info("log4j2的tps：在多线程异步的环境中：可以达到1800w, 是logback的10倍！！！{}", finalI);
//                        log.info("log4j2的tps：在多线程异步的环境中：可以达到1800w, 是logback的10倍！！！{}", finalI);
//                        log.info("log4j2的tps：在多线程异步的环境中：可以达到1800w, 是logback的10倍！！！{}", finalI);
//                        log.info("log4j2的tps：在多线程异步的环境中：可以达到1800w, 是logback的10倍！！！{}", finalI);
//                    });
//                }
//
//                try {
//                    Thread.sleep(5000);
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
//            }
//        }).start();

//        if((int)(Math.random() * 10) % 2 == 0 ) {
//            throw new Exception();
//        }
    }
}
