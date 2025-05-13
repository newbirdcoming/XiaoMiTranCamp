package com.example.daythree.rocketmqprovider;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(properties = {
        "rocketmq.name-server=10.16.6.91:9876"  // 指定测试环境的 IP 和端口
})
public class RocketMQProviderTest {

    @Autowired
    private SpringProducer producer;

    @Test
    public void testSendAndReceive() {
        String topic = "test051301";
        String message = "兰杰发送： RocketMQ!";

        producer.sendMessage(topic, message);

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}