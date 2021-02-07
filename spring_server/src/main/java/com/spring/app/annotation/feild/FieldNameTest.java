package com.spring.app.annotation.feild;

import org.springframework.util.StringUtils;
import java.util.stream.Stream;

/**
 * @author shenxie
 * @date 2021/2/5
 */
public class FieldNameTest {

    /**
     * 反射获取 ：在某个类上的属性的指定注解的值
     * @param args
     */
    public static void main(String[] args) {
        Stream.of(Order.class.getDeclaredFields()).forEach(filed -> {
            FieldName fieldName = filed.getAnnotation(FieldName.class);
            if (!StringUtils.isEmpty(fieldName)) {
                System.out.println(fieldName.name());
            }
        });
    }
}
