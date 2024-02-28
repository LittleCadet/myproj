package com.log4j2.app.log;

import com.log4j2.app.log.disruptor.DisruptorPublish;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author shenxie
 * @date 2024/2/28
 */
@RestController
public class Controller {

    private final DisruptorPublish disruptorPublish;


    public Controller(DisruptorPublish disruptorPublish) {
        this.disruptorPublish = disruptorPublish;
    }

    @GetMapping("disruptor")
    public String showInfo(){
        disruptorPublish.publish();
        return "disruptor事件发送成功了！！！";
    }
}
