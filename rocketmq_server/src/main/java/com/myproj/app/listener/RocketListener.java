package com.myproj.app.listener;

import com.myproj.app.service.RocketService;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * rocket 监听器
 *
 */
@Slf4j
public class RocketListener implements MessageListenerConcurrently
{
    @Autowired
    private RocketService rocketService;

    @Override
    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs,
        ConsumeConcurrentlyContext consumeConcurrentlyContext)
    {
        log.info("Consume message: {}", msgs.size());
        for (MessageExt msg : msgs)
        {
            String text = new String(msg.getBody());
            rocketService.getMwssage(text, msg.getTags());
        }
        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
    }

}
