package com.myproj.app.schedule;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * spring cron,fixed delay,fixed rated
 *
 * @author The flow developers
 */
@Component
public class Schedule {

    /**
     * fixedDelay：代表上一次任务结束时间到下一次任务的开始时间的时间间隔
     * 注意：天然适合于单线程
     */
    /*@Scheduled(initialDelay = 2000L, fixedDelay = 1000L)
    public void fixedDelay(){
        try {
            Thread.sleep(2 * 1000L);
            System.out.println(new Date() + "finish to execute fixedDelay task! " + Thread.currentThread().getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }*/

    /**
     * fixedRate:代表上一次任务开始时间到下一次任务的开始时间的时间间隔。
     * 注意：如果下一次任务应该开始执行了，但是没有执行，原因：spring的定时任务默认是单线程的，所以会自动阻塞等待上一个任务的完成，之后会立即执行下一个任务
     */
    /*@Scheduled(initialDelay = 2* 1000L,fixedRate = 1000L)
    public void fixedRate(){
        try {
            //Thread.sleep(2*1000L);
            System.out.println(new Date()+ "finish to execute fixedRate task!" + Thread.currentThread().getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/

    /**
     * cron:代表在某个固定的时间点执行，或者每隔多久执行一次
     * 注意：cron代表：到了执行的时间点，能执行，则执行，不能执行，会自动跳过该次执行
     */
    @Scheduled(cron = "*/1 * * * * *")
    public void cron(){
        try {
            Thread.sleep(2*1000L);
            System.out.println(new Date()+ "finish to execute cron task!" + Thread.currentThread().getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
