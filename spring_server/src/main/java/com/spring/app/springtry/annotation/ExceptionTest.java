package com.spring.app.springtry.annotation;

import lombok.extern.slf4j.Slf4j;

/**
 * @author shenxie
 * @date 2020/9/3
 */
@Slf4j
public class ExceptionTest {

    public static void main(String[] args) {
        RetryableException exception = new RetryableException();
        try {
            exception.exception();
        } catch (Exception e) {
           log.error("========捕获到异常======="  + e.getClass());
        }
    }
}
