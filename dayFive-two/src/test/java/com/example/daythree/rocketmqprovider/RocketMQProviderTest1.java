package com.example.daythree.rocketmqprovider;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RocketMQProviderTest1 {

    @Autowired
    private SpringProducer producer;

    @Test
    public void testSendMessage() {
        String topic = "test051301";
        String message = "我是兰杰 发送MQ";

        // 发送消息
        producer.sendMessage(topic, message);

        System.out.println("消息已发送到主题: " + topic);

        // 等待消费者处理（根据实际情况调整等待时间）
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}