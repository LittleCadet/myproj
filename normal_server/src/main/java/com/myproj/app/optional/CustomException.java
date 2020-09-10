package com.myproj.app.optional;

import lombok.Data;

/**
 * @author shenxie
 * @date 2020/9/7
 */
@Data
public class CustomException extends Exception{

    private String msg;

    private String code;
}
