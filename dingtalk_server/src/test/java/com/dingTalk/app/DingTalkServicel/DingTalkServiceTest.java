package com.dingTalk.app.DingTalkServicel;

import com.dingTalk.app.dingtalk.DingTalkService;
import com.dingTalk.app.exception.NetworkException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 钉钉测试类
 * @author LittleCadet
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class DingTalkServiceTest {

    @Autowired
    private DingTalkService dingTalkService;

    @Test
    public void getAccessToken(){
        try {
            System.out.println(dingTalkService.getAccessToken());
        } catch (NetworkException e) {
            Assert.fail("exception ! ");
        }
    }

    @Test
    public void sendMsg(){
        dingTalkService.sendMsgTODIngTalk(new Exception());
    }


}
