package com.spring.app.springTry;

/**
 *
 * 注意自定义异常要用 继承Exception，不能是Throwable,原因：在catch中不能捕获到，需要在大的Exception中才能捕获到
 * @author shenxie
 * @date 2020/5/21
 */
public class CustomException extends Exception {

    private String message;

    private String code;

    public CustomException(String code, String message){
        this.code = code;
        this.message = message;
    }

}
