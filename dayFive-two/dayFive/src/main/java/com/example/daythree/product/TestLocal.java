package com.example.daythree.product;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

public class TestLocal {
    public static void main(String[] args) throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer("ProducerGroup");
        producer.setNamesrvAddr("localhost:9876");
        producer.start();

        try {
            for (int i = 0; i < 10; i++) { // 发送多条消息便于测试
                Message msg = new Message(
                        "test0513",
                        "TagA",
                        ("消息-" + i).getBytes(RemotingHelper.DEFAULT_CHARSET)
                );
                SendResult result = producer.send(msg);
                System.out.printf("发送结果: %s, 消息ID: %s, 队列ID: %d%n",
                        result.getSendStatus(),
                        result.getMsgId(),
                        result.getMessageQueue().getQueueId());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            producer.shutdown();
        }
    }
}