package com.myproj.app.schedule;

import com.myproj.app.candp.RocketMessageConsumer;
import com.myproj.app.candp.RocketMessageProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @Author LittleCadet
 * @Date 2019/8/15
 */
@Slf4j
@Component
public class RocketSchedule
{
    @Autowired
    private RocketMessageConsumer consumer;

    @Scheduled(fixedRate = 1000L,initialDelay = 3000L)
    public void job(){
        log.info("job start");
        consumer.start();
        log.info("job end");
    }
}
