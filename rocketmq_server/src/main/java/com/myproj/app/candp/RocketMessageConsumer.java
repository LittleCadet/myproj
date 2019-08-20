package com.myproj.app.candp;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.consumer.listener.MessageListenerOrderly;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;

/**
 * RocketMQ Consumer
 *
 */
@Slf4j
public class RocketMessageConsumer {
    private DefaultMQPushConsumer consumer;

    private String topic;

    private boolean started;

    public RocketMessageConsumer(String nameServerAddr, String group, String topic) {
        consumer = new DefaultMQPushConsumer(group);
        consumer.setNamesrvAddr(nameServerAddr);
        this.topic = topic;

        // 不使用VIP
        consumer.setVipChannelEnabled(false);

        started = false;
    }

    public void registerMessageListener(MessageListenerConcurrently listener) {
        consumer.registerMessageListener(listener);
    }

    public void registerMessageListener(MessageListenerOrderly listener) {
        consumer.registerMessageListener(listener);
    }

    public void start() {
        if (started) {
            return;
        }
        try {
            /**
             * 设置Consumer第一次启动是从队列头部开始消费还是队列尾部开始消费
             * 如果非第一次启动，那么按照上次消费的位置继续消费
             */
            consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);

            // 设置为集群消费模式【默认就是集群的消费方式：平均消费】
            consumer.setMessageModel(MessageModel.CLUSTERING);

            //订阅topic和tag【这里tag用*全匹配】
            consumer.subscribe(topic, "*");
            consumer.start();
            started = true;
            log.info("Rocket MQ Consumer started: {}, {}, {}",
                    consumer.getConsumerGroup(), topic, consumer.getNamesrvAddr());
        } catch (MQClientException e) {
            log.error("MQ error", e);
        }
    }

    public void shutdown() {
        consumer.shutdown();
        started = false;
        log.info("Rocket MQ Consumer shut down");
    }
}
