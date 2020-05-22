package com.spring.app.springtry;

import lombok.extern.slf4j.Slf4j;
import org.springframework.retry.RetryCallback;
import org.springframework.retry.RetryContext;
import org.springframework.retry.RetryListener;

/**
 * 监听器的实现
 * @author shenxie
 * @date 2020/5/21
 */
@Slf4j
public class CustomRetryListener implements RetryListener {

    /**
     * 代表监听器，开始运行
     * @param retryContext
     * @param retryCallback
     * @param <T>
     * @param <E>
     * @return
     */
    @Override
    public <T, E extends Throwable> boolean open(RetryContext retryContext, RetryCallback<T, E> retryCallback) {
        log.info("监听器开始运行");
        return true;
    }

    @Override
    public <T, E extends Throwable> void close(RetryContext retryContext, RetryCallback<T, E> retryCallback, Throwable throwable) {
        int count = retryContext.getRetryCount();
        if(count > 0){
            log.info(String.format("共进行了【%s】次重试，最后一次发生异常=%s", retryContext.getRetryCount(), retryContext.getLastThrowable()));
        }
    }

    /**
     *
     * @param retryContext
     * @param retryCallback
     * @param throwable
     * @param <T>
     * @param <E>
     */
    @Override
    public <T, E extends Throwable> void onError(RetryContext retryContext, RetryCallback<T, E> retryCallback, Throwable throwable) {
        log.error(String.format("正在执行第【%s】次重试", retryContext.getRetryCount()));
    }
}
