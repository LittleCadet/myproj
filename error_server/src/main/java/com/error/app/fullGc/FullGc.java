package com.error.app.fullGc;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

/**
 * @author shenxie
 * @date 2021/11/18
 */
@Service
public class FullGc implements InitializingBean {

    @Override
    public void afterPropertiesSet() throws Exception {
        while(true){
            Thread.sleep(10 * 1000);
            System.gc();
            System.out.println("fullGc执行完成");
        }
    }
}
