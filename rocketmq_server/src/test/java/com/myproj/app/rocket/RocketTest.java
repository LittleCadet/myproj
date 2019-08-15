package com.myproj.app.rocket;

import com.myproj.app.candp.RocketMessageProducer;
import com.myproj.app.constant.RocketConstants;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author LittleCadet
 * @Date 2019/8/15
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RocketTest
{
    @Autowired
    private RocketMessageProducer producer;

    @Test
    public void send()
        throws InterruptedException, MQBrokerException
    {
        for(int i = 1 ;i <= 5; i++){
            //顺序消费：用同步发送消息同一个队列中，消费时，使用顺序消费即可
            //producer.send(RocketConstants.TOPIC_ROCKET, RocketConstants.TAG_ROCKET, "send " + i + " message");

            //普通消费：异步发送
            producer.sendAsync(RocketConstants.TOPIC_ROCKET, RocketConstants.TAG_ROCKET, "send " + i + " message");
        }

        Thread.sleep(30_000L);
    }
}
