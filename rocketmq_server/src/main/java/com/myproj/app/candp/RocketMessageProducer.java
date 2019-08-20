package com.myproj.app.candp;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

/**
 * RocketMQ Producer
 *
 */
@Slf4j
public class RocketMessageProducer {

    private DefaultMQProducer producer;

    private SendCallback defaultSendCallback;

    private boolean started;

    private ReentrantLock lock;

    public RocketMessageProducer(String nameServerAddr, String group, int timeout) {
        producer = new DefaultMQProducer(group);
        producer.setNamesrvAddr(nameServerAddr);

        // 默认开启VIP，连接内部IP的10909端口
        producer.setVipChannelEnabled(false);
        // 默认3秒，经常timeout
        producer.setSendMsgTimeout(timeout);

        defaultSendCallback = new SilentSendCallback();

        lock = new ReentrantLock();
        started = false;
    }

    public void start() {
        if (started) {
            return;
        }
        lock.lock();
        if (started) {
            return;
        }
        try {
            producer.start();
            started = true;
            log.info("Rocket MQ Producer started: {}, {}, {}",
                    producer.getNamesrvAddr(), producer.getProducerGroup(), producer.getSendMsgTimeout());
        } catch (MQClientException e) {
            log.error("MQ error", e);
        }
        lock.unlock();
    }

    public void shutdown() {
        producer.shutdown();
        started = false;
        log.info("Rocket MQ Producer shutdown");
    }

    /**
     * 发送同步消息：用于顺序消费
     *
     * @param topic
     * @param tag
     * @param payload
     */
    public void send(String topic, String tag, Object payload) {
        start();
        try {
            String text;
            if (payload instanceof String) {
                text = (String) payload;
            } else {
                text = JSON.toJSONString(payload);
            }
            byte[] content = text.getBytes(RemotingHelper.DEFAULT_CHARSET);
            Message msg = new Message(topic, tag, content);

            //不保证顺序消费（广播消息）
            //SendResult result = producer.send(msg);

            //保证顺序消费（顺序消息）
            SendResult result = producer.send(msg, new MessageQueueSelector() {
                public MessageQueue select(List<MessageQueue> mqs, Message msg, Object arg) {
                    Integer id = (Integer) arg;
                    int index = id % mqs.size();
                    return mqs.get(index);
                }
            }, 0);
            log.info("Send message: {}, {}, {}", topic, tag, text);
            log.info("{}", result.toString());
            // TODO：失败重试
        } catch (UnsupportedEncodingException| MQClientException| RemotingException| MQBrokerException| InterruptedException e) {
            log.error("Send error", e);
        }
    }

    /**
     * 发送异步消息
     *
     * @param topic
     * @param tag
     * @param payload
     */
    public void sendAsync(String topic, String tag, Object payload)
        throws MQBrokerException
    {
        start();
        try {
            String text;
            if (payload instanceof String) {
                text = (String) payload;
            } else {
                text = JSON.toJSONString(payload);
            }
            byte[] content = text.getBytes(RemotingHelper.DEFAULT_CHARSET);
            Message msg = new Message(topic, tag, content);
            //producer.send(msg, defaultSendCallback);

            producer.send(msg, new MessageQueueSelector() {
                public MessageQueue select(List<MessageQueue> mqs, Message msg, Object arg) {
                    Integer id = (Integer) arg;
                    int index = id % mqs.size();
                    return mqs.get(index);
                }
            }, 0,defaultSendCallback);
            log.info("send message: {}, {}, {}", topic, tag, text);
        } catch (UnsupportedEncodingException| MQClientException| RemotingException| InterruptedException e) {
            log.error("send error", e);
        }
    }

    private static class SilentSendCallback implements SendCallback {
        @Override
        public void onSuccess(SendResult result) {
            log.info("{}", result.toString());
        }
        @Override
        public void onException(Throwable throwable) {
            log.error("send error", throwable);
            // TODO：失败重试
        }
    }

    /**
     * 发送单向消息：用于不关注发送消息的结果
     *
     * @param topic
     * @param tag
     * @param payload
     */
    public void sendOneWay(String topic, String tag, Object payload) {
        start();
        try {
            String text;
            if (payload instanceof String) {
                text = (String) payload;
            } else {
                text = JSON.toJSONString(payload);
            }
            byte[] content = text.getBytes(RemotingHelper.DEFAULT_CHARSET);
            Message msg = new Message(topic, tag, content);
            producer.sendOneway(msg);
            log.info("send message: {}, {}, {}", topic, tag, text);
        } catch (UnsupportedEncodingException| MQClientException| RemotingException| InterruptedException e) {
            log.error("send error", e);
        }
    }

    /**
     * 发送延迟消息：即为发送定时消息
     *
     * @param topic
     * @param tag
     * @param payload
     * @param level
     */
    public void sendDelay(String topic, String tag, Object payload, int level) {
        start();
        try {
            String text;
            if (payload instanceof String) {
                text = (String) payload;
            } else {
                text = JSON.toJSONString(payload);
            }
            byte[] content = text.getBytes(RemotingHelper.DEFAULT_CHARSET);
            Message msg = new Message(topic, tag, content);
            msg.setDelayTimeLevel(level);
            SendResult result = producer.send(msg);
            log.info("send message: {}, {}, {}", topic, tag, text);
            log.info("{}", result.toString());
            // TODO：失败重试
        } catch (UnsupportedEncodingException| MQClientException| RemotingException| MQBrokerException| InterruptedException e) {
            log.error("send error", e);
        }
    }
}
