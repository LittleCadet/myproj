package com.myproj.sms;

import com.myproj.sms.service.SendMessageService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @Description:
 * @Author: shenxie
 * @CreateDate: 2020/1/6
 * @UpdateUser:
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class SendMessageTest {

    @Resource
    private SendMessageService sendMessageService;

    @Test
    public void sendCode(){
        sendMessageService.sendVerifyCode("18655755468");
    }

    /*@Test
    public void getResult(){
        sendMessageService.getCache("18655755468","111568");
    }*/
}
