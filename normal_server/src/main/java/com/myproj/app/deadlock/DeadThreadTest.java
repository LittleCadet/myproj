package com.myproj.app.deadlock;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * @Author LittleCadet
 * @Date 2020/4/9
 */
@Component
public class DeadThreadTest implements ApplicationRunner
{
    @Override
    public void run(ApplicationArguments args)
        throws Exception
    {
        System.out.println("======================开始执行死锁================");
        DeadThread dt1 = new DeadThread();
        dt1.setFlag("a");
        Thread t1 = new Thread(dt1);
        t1.start();

        try
        {
            Thread.sleep(2000);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }

        dt1.setFlag("b");
        Thread t2 = new Thread(dt1);

        t2.start();
    }
}
