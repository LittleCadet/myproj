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

    /**
     * 顺序消费
     * @throws InterruptedException
     * @throws MQBrokerException
     */
    @Test
    public void sendSequence()
        throws InterruptedException, MQBrokerException
    {
        for(int i = 1 ;i <= 5; i++){
            //顺序消费：用同步发送消息同一个队列中，消费时，使用顺序消费即可
            producer.send(RocketConstants.TOPIC_ROCKET, RocketConstants.TAG_ROCKET, "send " + i + " message");
        }

        Thread.sleep(30_000L);
    }

    /**
     * 异步发送消息
     * @throws InterruptedException
     * @throws MQBrokerException
     */
    @Test
    public void sendAsyn()
        throws InterruptedException, MQBrokerException
    {
        for(int i = 1 ;i <= 5; i++){

            //普通消费：异步发送
            producer.sendAsync(RocketConstants.TOPIC_ROCKET, RocketConstants.TAG_ROCKET, "send " + i + " message");

        }

        Thread.sleep(30_000L);
    }

    /**
     * 定时消费：延迟消费：指的是消息发送是及时的，但是消费是根据延迟级别延迟的（延迟级别从1开始）
     * @throws InterruptedException
     * @throws MQBrokerException
     */
    @Test
    public void sendSchedule()
        throws InterruptedException, MQBrokerException
    {
        for(int i = 1 ;i <= 5; i++){

            producer.sendDelay(RocketConstants.TOPIC_ROCKET, RocketConstants.TAG_ROCKET, "send " + i + " message",4);

        }

        Thread.sleep(60_000L);
    }
}
