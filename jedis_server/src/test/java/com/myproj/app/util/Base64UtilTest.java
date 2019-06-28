package com.myproj.app.util;

import com.myproj.app.tools.Base64Util;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.UnsupportedEncodingException;

/**
 * @author LittleCadet
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class Base64UtilTest {

    private String password = "redisOfEdwardShen";

    @Test
    public void encodec(){
        try {
            System.out.println("encodec:" + Base64Util.encodec(password));
        } catch (UnsupportedEncodingException e) {
            Assert.fail("failed to encodec");
        }
    }

    @Test
    public void decodec(){
        try {
            System.out.printf("decodec:" + Base64Util.decodec("cmVkaXNPZkVkd2FyZFNoZW4="));
        } catch (UnsupportedEncodingException e) {
            Assert.fail("failed to decodec");
        }
    }

}
