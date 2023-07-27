package com.spring;

import com.spring.app.publish.springpublish.event.MessageEvent;
import com.spring.app.publish.springpublish.publisher.MessagePublish;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author shenxie
 * @date 2020/5/21
 */
@SpringBootTest(classes = {PublishTest.class})
@RunWith(SpringRunner.class)
public class PublishTest {

    @Autowired
    private MessagePublish publish;

    @Test
    public void send(){
        publish.send(new MessageEvent(this,"0000", "成功事件"));
    }
}
