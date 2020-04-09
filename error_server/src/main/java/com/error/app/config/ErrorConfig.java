package com.error.app.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author LittleCadet
 * @Date 2020/3/25
 */
@Slf4j
@Component
@ResponseBody
@ControllerAdvice
public class ErrorConfig
{

    @ExceptionHandler(Exception.class)
    public String exceptionHandler(){
        log.info("=============进入Exception ==================");
        return "Exception";
    }

    @ExceptionHandler(NullPointerException.class)
    public String nullPointerExceptionHandler(){
        log.info("=============进入NullPointerException ==================");
        return "NullPointerException";
    }
}
