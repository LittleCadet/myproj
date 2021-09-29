package com.spring.app.properties;

import lombok.Data;
import lombok.ToString;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Properties;

/**
 * 自定义读取配置文件的配置，并加载到bean中
 * @author shenxie
 * @date 2021/9/29
 */
public class CustomProperty {


    public static void main(String[] args) throws IOException, IllegalAccessException, InstantiationException {
        // 加载配置文件， 并读取到properties中
        Properties properties = new Properties();
        properties.load(new FileReader("/Users/littlecadet/workspace/self/myproj/spring_server/target/classes/customer.properties"));

        // 将properites中的配置项，注入到bean中
        Class<Health> healthClass = Health.class;
        Health health = healthClass.newInstance();
        Field[] declaredFields = healthClass.getDeclaredFields();
        Method[] methods = healthClass.getDeclaredMethods();

        for (Field field : declaredFields) {
            String value = properties.getProperty(field.getName());
            Arrays.stream(methods).filter(m -> m.getName().startsWith("set")).filter(m -> m.getName().toLowerCase().endsWith(field.getName())).forEach(m -> {
                try {
                    m.setAccessible(true);
                    m.invoke(health, value);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            });
        }

        System.out.println(health);

    }

    @ToString
    @Data
    public static class Health {
        private String app;

        private String desc;
    }
}
