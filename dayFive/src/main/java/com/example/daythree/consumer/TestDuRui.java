package com.example.daythree.consumer;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.remoting.common.RemotingHelper;

import java.io.UnsupportedEncodingException;
import java.util.List;

public class TestDuRui {
    public static void main(String[] args) throws InterruptedException, MQClientException {
        // 构建消息消费者
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("DemoConsumer");

        // 配置NameServer地址
        consumer.setNamesrvAddr("localhost:9876");
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET);

        // 修改订阅的Topic为test0513（关键修改点）
        consumer.subscribe("test0513", "*"); // 接收test0513主题下的所有消息

        // 注册消息监听回调
        consumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(
                    List<MessageExt> msgs,
                    ConsumeConcurrentlyContext context
            ) {
                msgs.forEach(messageExt -> {
                    try {
                        String messageBody = new String(
                                messageExt.getBody(),
                                RemotingHelper.DEFAULT_CHARSET
                        );
                        System.out.println("收到消息: " + messageBody);
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace(); // 建议打印异常信息
                    }
                });
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });

        // 启动消费者
        consumer.start();
        System.out.println("Consumer Started");
    }
}