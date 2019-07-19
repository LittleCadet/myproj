package com.dingTalk.app.exception;

/**
 * 网络异常
 * @author LittleCadet
 */
public class NetworkException extends AbstractException {

    public NetworkException(Integer code,String msg) {
        super(code,msg);
    }
}
