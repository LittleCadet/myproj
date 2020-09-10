package com.myproj.app.optional;

import java.util.Optional;

/**
 * @author shenxie
 * @date 2020/9/7
 */
public class OptionalDemo {

    public static void main(String[] args) throws CustomException {
        UserInfo userInfo = new UserInfo();
        userInfo.setAge(1);
        userInfo.setName("张三");

        //适用于对象中套用对象 ， 并优雅判定每个对象是否为空
        /*Optional.ofNullable(userInfo)
                .map(a -> a.getUserInfo())
                .map(b -> b.getName())
                .orElseThrow(() -> new CustomException());*/

        Optional.ofNullable(userInfo)
                .map(a -> a.getName())
                .orElseThrow(() -> new CustomException());
    }
}
