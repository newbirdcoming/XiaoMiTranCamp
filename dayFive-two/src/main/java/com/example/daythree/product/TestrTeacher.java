package com.example.daythree.product;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

public class TestrTeacher {
    public static void main(String[] args) throws Exception {
        // 创建生产者实例并指定组名
        DefaultMQProducer producer = new DefaultMQProducer("ProducerGroup");

        // 设置NameServer地址为目标IP（修改这里）
        producer.setNamesrvAddr("10.16.6.91:9876");

        // 启动生产者
        producer.start();

        try {
            // 创建消息对象，指定主题、标签和内容
            Message msg = new Message(
                    "test0513",  // 主题
                    "兰杰",       // 标签（可选）
                    "我是兰杰 MQ #####################################".getBytes(RemotingHelper.DEFAULT_CHARSET)  // 消息内容
            );

            // 发送消息并获取结果
            SendResult sendResult = producer.send(msg);
            System.out.printf("发送结果: %s%n", sendResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭生产者
            producer.shutdown();
        }
    }
}