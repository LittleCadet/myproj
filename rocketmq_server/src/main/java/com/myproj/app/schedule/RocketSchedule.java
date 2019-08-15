package com.myproj.app.schedule;

import com.myproj.app.candp.RocketMessageConsumer;
import com.myproj.app.candp.RocketMessageProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 用定时任务保持消费者处于激活状态:实质上是为了主动调用RocketMessageConsumer的start(),启动消费。
 * @Author LittleCadet
 * @Date 2019/8/15
 */
@Slf4j
@Component
public class RocketSchedule
{
    @Autowired
    private RocketMessageConsumer rocketConsumer;

    @Scheduled(fixedRate = 60_000L,initialDelay = 3000L)
    public void job(){
        log.info("job start");
        rocketConsumer.start();
        log.info("job end");
    }
}
