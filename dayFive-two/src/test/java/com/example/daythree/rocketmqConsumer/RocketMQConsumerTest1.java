package com.example.daythree.rocketmqConsumer;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.test.context.TestPropertySource;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@TestPropertySource(properties = {
        "rocketmq.name-server=localhost:9876"
})
public class RocketMQConsumerTest1 {

    private static final AtomicReference<String> consumedMessage = new AtomicReference<>();
    private static final CountDownLatch latch = new CountDownLatch(1);

    @Test
    public void testMessageConsumption() throws InterruptedException {
        boolean messageReceived = latch.await(30, TimeUnit.SECONDS);
        assertEquals(true, messageReceived, "在超时时间内未收到消息");
        System.out.println("成功消费消息: " + consumedMessage.get());
    }

    // 测试专用的消费者，显式指定 Topic 和消费者组
    @Bean
    public RocketMQListener<String> testConsumer() {
        return new RocketMQListener<String>() {
            @Override
            public void onMessage(String message) {
                System.out.println("测试消费者收到消息: " + message);
                consumedMessage.set(message);
                latch.countDown();
            }
        };
    }
}