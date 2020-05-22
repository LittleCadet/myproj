package com.spring.app.springtry;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.retry.backoff.FixedBackOffPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;

import java.util.Collections;

/**
 * @author shenxie
 * @date 2020/5/20
 */
@Slf4j
@Configuration
public class TryConfig implements InitializingBean , Ordered {

    private static RetryTemplate retryTemplate = new RetryTemplate();

    public RetryTemplate getInstance(){
        return retryTemplate;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("===============开始 初始化tryConfig================");
        //指定重试策略： 默认：SimpleRetryPolicy为3次重试
        retryTemplate.setRetryPolicy(new SimpleRetryPolicy(3, Collections.singletonMap(CustomException.class,true)));
        //指定回退策略：默认FixedBackOffPolicy （让当前线程睡眠1s）
        retryTemplate.setBackOffPolicy(new FixedBackOffPolicy());
        retryTemplate.setThrowLastExceptionOnExhausted(true);
        //注册监听器
        retryTemplate.registerListener(new CustomRetryListener());
        log.info("===============结束 初始化tryConfig================");
    }

    @Override
    public int getOrder() {
        return 1;
    }
}
