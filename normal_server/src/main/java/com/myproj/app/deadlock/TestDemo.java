package com.myproj.app.deadlock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

/**
 *
 * InitializingBean : 是在bean加载的时候，执行
 *
 * @author shenxie
 * @date 2020/9/24
 */
//@Service
public class TestDemo implements InitializingBean{

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("======InitializingBean开始执行死锁=======");
        DeadThread dt1 = new DeadThread();
        dt1.setFlag("a");
        Thread t1 = new Thread(dt1);
        t1.start();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        dt1.setFlag("b");
        Thread t2 = new Thread(dt1);

        t2.start();
    }
}
