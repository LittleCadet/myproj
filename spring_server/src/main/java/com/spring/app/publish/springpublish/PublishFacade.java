package com.spring.app.publish.springpublish;

import com.spring.app.publish.springpublish.event.MessageEvent;
import com.spring.app.publish.springpublish.publisher.MessagePublish;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author shenxie
 * @date 2023/6/19
 */
@RestController
public class PublishFacade {

    @Autowired
    private MessagePublish messagePublish;

    @GetMapping("/spring/publish")
    public Boolean testSpringPublish(){
        messagePublish.send(new MessageEvent(this,"0000", "成功事件"));
        return true;
    }
}
