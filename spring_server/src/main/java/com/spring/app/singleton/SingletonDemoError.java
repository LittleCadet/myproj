package com.spring.app.singleton;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;

/**
 * @author shenxie
 * @date 2020/10/25
 */
//@Service
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class SingletonDemoError {

    @Autowired
    private OrderService orderService;

    //private Void param = orderService.orderInfo();

    public SingletonDemoError() {
        this.orderService = orderService;
        orderService.orderInfo();
    }
}
