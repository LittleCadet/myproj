package com.dingTalk.app.exception;

import lombok.Data;

/**
 * 异常的父类
 * @author LittleCadet
 */
@Data
public abstract class AbstractException extends Exception{

    private Integer code;

    public  String message;

    public AbstractException() {
    }

    public AbstractException(Integer code,String message) {
        super("{code:" + code + ", msg:" + message + "}");
        this.message = message;
        this.code = code;
    }
}
