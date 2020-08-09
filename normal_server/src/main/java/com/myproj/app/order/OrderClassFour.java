package com.myproj.app.order;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @Author LittleCadet
 * @Date 2020/2/21
 */
@Order(-100)
@Component
public class OrderClassFour implements OrderInterface
{
    @Override
    public String info()
    {
        return "OrderClassFour";
    }

    @Override
    public void run(String... args)
        throws Exception
    {
        System.out.println("=======================OrderClassFour========================");
    }
}
