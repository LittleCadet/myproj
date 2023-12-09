package com.myproj.app.order;

import com.google.common.collect.Lists;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

/**
 * @Author LittleCadet
 * @Date 2020/2/21
 */
@Order(4)
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

    public static void main(String[] args) {
        List<String> tmp = Lists.newArrayList("OrderClassThree" , "OrderClassFour" , "OrderClassTwo" ,"OrderClassOne" , "ReloadingPropertyPlaceholderConfigurer" , "ClientSsoEnvironmentPrepareListener" , "1" ,"2");
        Collections.sort(tmp);
        System.out.println(tmp);
    }
}
