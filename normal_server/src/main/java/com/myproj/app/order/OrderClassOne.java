package com.myproj.app.order;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @Author LittleCadet
 * @Date 2020/2/21
 */
@Order(1)
@Component
public class OrderClassOne implements OrderInterface
{
    @Override
    public String info()
    {
        return "OrderClassOne";
    }

    @Override
    public void run(String... args)
        throws Exception
    {
        System.out.println("========================OrderClassOne======================");
    }
}
