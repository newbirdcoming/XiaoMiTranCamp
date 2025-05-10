// package org.example.event;
//
// import org.springframework.beans.factory.SmartInitializingSingleton;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
// import org.springframework.context.event.SimpleApplicationEventMulticaster;
// import org.springframework.context.support.AbstractApplicationContext;
// import org.springframework.scheduling.concurrent.CustomizableThreadFactory;
// import org.springframework.stereotype.Component;
//
// import java.util.concurrent.LinkedBlockingQueue;
// import java.util.concurrent.ThreadPoolExecutor;
// import java.util.concurrent.TimeUnit;
//
// @Component
// public class AutowireStaticSmartInitializingSingleton implements SmartInitializingSingleton {
//
//     public static final String THREAD_APPLICATION_EVENT_MULTICASTER = "threadApplicationEventMulticaster";
//     @Autowired
//     private ConfigurableListableBeanFactory beanFactory;
//
//     @Autowired
//     private AbstractApplicationContext applicationContext;
//
//
//     @Override
//     public void afterSingletonsInstantiated() {
//         SimpleApplicationEventMulticaster eventMulticaster = new SimpleApplicationEventMulticaster();
//         applicationContext.getApplicationListeners().forEach(eventMulticaster::addApplicationListener);
//         eventMulticaster.setTaskExecutor(new ThreadPoolExecutor(5, 10, 1000, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(1000), new CustomizableThreadFactory("threadApplicationEventMulticaster")));
//         beanFactory.registerSingleton(THREAD_APPLICATION_EVENT_MULTICASTER, eventMulticaster);
//     }
// }