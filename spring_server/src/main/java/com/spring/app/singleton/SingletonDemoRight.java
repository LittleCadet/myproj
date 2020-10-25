package com.spring.app.singleton;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

/**
 * @author shenxie
 * @date 2020/10/25
 */
@Service
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class SingletonDemoRight {


    private OrderService orderService;

    //private Void param = orderService.orderInfo();


    /**
     * 初始化顺序：
     *
     * 静态变量或静态语句块–>实例变量或初始化语句块–>构造方法–>@Autowired -> @Resource
     *
     * @param orderService
     */
    @Autowired
    public SingletonDemoRight(OrderService orderService) {
        this.orderService = orderService;
        orderService.orderInfo();
    }
}
