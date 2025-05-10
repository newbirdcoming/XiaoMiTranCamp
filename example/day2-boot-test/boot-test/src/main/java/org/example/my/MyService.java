//package org.example.my;
//
//import org.springframework.context.ApplicationContext;
//import org.springframework.stereotype.Service;
//import org.example.event.DemoEvent;
//
//@Service
//public class MyService {
//
//    private final ApplicationContext applicationContext;
//
//    public MyService(ApplicationContext applicationContext) {
//        this.applicationContext = applicationContext;
//    }
//
//    public void doBusinessLogic() {
//        // 1. 执行业务操作
//        String message = "重要通知";
//
//        // 2. 发布事件（使用 applicationContext）
//        applicationContext.publishEvent(new DemoEvent(this, message, applicationContext));
//
//        // 3. 主线程继续执行，不会等待监听器处理完成
//    }
//}