package com.example.daythree;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//public class DayFiveTwoApplication implements CommandLineRunner {
public class DayFiveTwoApplication  {
//    @Autowired
//    private SpringProducer producer;


    public static void main(String[] args) {
        SpringApplication.run(DayFiveTwoApplication.class, args);
    }


//    @Override
//    public void run(String... args) throws Exception {
//        String topic = "TestTopic";
//        String message = "启动测试消息";
//
//        producer.sendMessage(topic, message);
//        System.out.println("已发送测试消息: " + message);
//    }
}
