package com.spring.app.annotation.method;


import java.util.Map;

/**
 * @author shenxie
 * @date 2021/1/25
 */
public class AnnotationDemo {

    @MethodHandler(name = "superHandler")
    public  String showInfo(Map<String,Object> params){
        return "result: " + params;
    }

    public String showInfo2(Map<String,Object> params){
        return "showInfo2";
    }

}
