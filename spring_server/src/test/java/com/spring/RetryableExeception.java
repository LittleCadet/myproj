package com.spring;

import com.spring.app.springtry.annotation.RetryableException;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author shenxie
 * @date 2020/9/3
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {RetryableExeception.class})
public class RetryableExeception {

    @Autowired
    private RetryableException retryableException;

    @Test
    public void exception()  {
        try {
            retryableException.exception();
        } catch (Exception e) {
            System.out.println("捕获异常 ，" + e.getClass());
        }
    }
}
