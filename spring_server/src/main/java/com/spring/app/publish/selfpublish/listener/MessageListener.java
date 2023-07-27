package com.spring.app.publish.selfpublish.listener;


import com.spring.app.publish.selfpublish.event.SelfMessageEvent;

/**
 * 监听器的抽象版
 * @author shenxie
 * @date 2023/6/19
 */
public interface MessageListener {

    void action(SelfMessageEvent messageEvent);

}
