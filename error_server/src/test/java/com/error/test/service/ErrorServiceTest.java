package com.error.test.service;

import com.error.app.ErrorServerApplication;
import com.error.app.service.ErrorService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author LittleCadet
 * @Date 2020/3/25
 */
@SpringBootTest(classes = ErrorServerApplication.class)
@RunWith(SpringRunner.class)
public class ErrorServiceTest
{
    @Autowired
    private ErrorService errorService;

    @Test
    public void showInfo(){
        errorService.showInfo();
    }
}
