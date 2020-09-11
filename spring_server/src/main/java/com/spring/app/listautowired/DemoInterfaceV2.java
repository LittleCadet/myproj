package com.spring.app.listautowired;

import org.springframework.stereotype.Service;

/**
 * @author shenxie
 * @date 2020/9/10
 */
@Service
public class DemoInterfaceV2 implements DemoInterface{
    @Override
    public String showInfo() {
        return this.getClass().getCanonicalName();
    }
}
