package com.spring.app.springtry.annotation;

import lombok.Data;

/**
 * @author shenxie
 * @date 2020/9/3
 */
@Data
public class CustomException extends Exception {

    private String code;

    private String message;
}
