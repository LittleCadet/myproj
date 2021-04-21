package com.spring.app.annotation.clazz;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author shenxie
 * @date 2021/4/21
 */
@Data
// 在ConfigurationProperties中： 属性和yml中配置的属性： 可以使用"_","-","/"等字符分隔开，不影响spring的解析
@ConfigurationProperties(prefix = "test.io")
@Component
public class TestConfiguration {

    private String[] userName;

    private Integer age;
}


