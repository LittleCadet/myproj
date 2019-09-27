package com.myproj.app.sensitive;

import org.junit.Test;

/**
 * LittleCadet
 */
public class SensitiveTest {


    @Test
    public void cardNum(){
        System.out.println(Sensitive.cardNum("340111189808058545"));
    }

    @Test
    public void phoneNum(){
        System.out.println(Sensitive.phoneNum("4289568"));
    }

    @Test
    public void cellPhoneNum(){
        System.out.println(Sensitive.cellPhoneNum("18758986589"));
    }
}
