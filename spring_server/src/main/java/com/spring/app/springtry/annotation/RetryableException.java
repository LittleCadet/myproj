package com.spring.app.springtry.annotation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

import java.util.Date;

/**
 * @author shenxie
 * @date 2020/9/3
 */
@Slf4j
@Service
public class RetryableException {


    @Retryable(maxAttempts = 2 , value = CustomException.class , backoff = @Backoff(value = 500 , multiplier = 1))
    public void exception() throws Exception {

        log.info(String.format("运行ing , 时间 ： %s" , new Date()));

        throw new CustomException();

        /*for(int i = 0 ; i < 3 ; i++){
            if(0 != i % 2){

                throw new CustomException();
            }else{
                throw new Exception();
            }
        }*/
    }

    @Recover
    public void recover(){
        System.out.println("重试完成");
    }
}
