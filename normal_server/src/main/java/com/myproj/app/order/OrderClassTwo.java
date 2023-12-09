package com.myproj.app.order;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @Author LittleCadet
 * @Date 2020/2/21
 */
@Order(2)
@Component
public class OrderClassTwo implements OrderInterface
{
    @Override
    public String info()
    {
        return "OrderClassTwo";
    }

    @Override
    public void run(String... args)
        throws Exception
    {
        System.out.println("=======================OrderClassTwo========================");
    }
}
