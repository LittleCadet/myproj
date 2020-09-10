package com.myproj.app.optional;

import lombok.Data;

/**
 * @author shenxie
 * @date 2020/9/7
 */
@Data
public class UserInfo {

    private String name;

    private Integer age;

    private UserInfo userInfo;
}
