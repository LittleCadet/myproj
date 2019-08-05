package com.myproj.app.service.consumer;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;

/**
 * 消息处理类
 * @author LittleCadet
 */
public class ProcessMessage implements MessageListener {

    @Override
    public void onMessage(Message message, byte[] bytes) {
        System.out.println("consume message : " + message + "bytes :" + bytes.toString());
    }
}
