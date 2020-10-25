package com.spring.app.singleton;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

/**
 * @author shenxie
 * @date 2020/10/25
 */
@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class OrderService {

    Void orderInfo(){
        System.out.println("=========执行了OrderService=========");
        return null;
    }
}
