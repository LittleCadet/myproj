package com.spring.app.springtry;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author shenxie
 * @date 2020/5/21
 */
@Slf4j
@Service
public class TryImpl implements TryFacade {

    @Override
    /*@Retryable(value = {CustomException.class} , backoff  = @Backoff(multiplier = 1))*/
    public int customException() throws CustomException {
        log.error("自定义异常抛出");
        throw new CustomException("10001", "自定义异常抛出");
        //return 1;
    }
}
