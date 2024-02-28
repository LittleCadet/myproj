package com.log4j2.app.log;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.boot.context.event.ApplicationStartingEvent;
import org.springframework.context.ApplicationListener;

/**
 * @author shenxie
 * @date 2024/2/26
 */
@Slf4j
public class Log4j2ApplicationListener implements ApplicationListener<ApplicationStartingEvent> {

    @Override
    public void onApplicationEvent(ApplicationStartingEvent event) {
        log.info("===============>> {}日志输出了", this.getClass().getName());
    }
}
