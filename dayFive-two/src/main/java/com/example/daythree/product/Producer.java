package com.example.daythree.product;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

public class Producer {
    public static void main(String[] args) throws MQClientException, InterruptedException {
        // 初始化一个消息生产者
        DefaultMQProducer producer = new DefaultMQProducer("DemoProducer");
        // 指定nameserver地址
        producer.setNamesrvAddr("localhost:9876");
        // 启动消息生产者服务
        producer.start();

        for (int i = 0; i < 2; i++) {
            try {
                // 创建消息。使用具体实现类 org.apache.rocketmq.common.message.Message
                Message msg = new org.apache.rocketmq.common.message.Message(
                        "TopicTest",
                        "TagA",
                        ("Hello RocketMQ " + i).getBytes(RemotingHelper.DEFAULT_CHARSET)
                );

                // 发送消息，获取发送结果
                SendResult sendResult = producer.send(msg);
                System.out.printf("%s%n", sendResult);
            } catch (Exception e) {
                e.printStackTrace();
                Thread.sleep(1000);
            }
        }

        // 消息发送完后，停止消息生产者服务
        producer.shutdown();
    }
}