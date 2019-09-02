package com.myproj.app.schedule;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTask;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.util.concurrent.Executors;

/**
 * @Scheduled使用多线程执行
 * @Author LittleCadet
 * @Date 2019/8/27
 */
@Slf4j
@Configuration
public class ScheduledConfig implements SchedulingConfigurer
{
    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar)
    {
        log.info("============init SchedulingConfigurer ============");
        scheduledTaskRegistrar.setScheduler(Executors.newScheduledThreadPool(10));
    }
}
