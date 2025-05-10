package org.example.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.context.event.SimpleApplicationEventMulticaster;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Configuration
public class AsyncEventConfig {
    public static final String THREAD_APPLICATION_EVENT_MULTICASTER = "threadApplicationEventMulticaster";

    @Autowired
    private AbstractApplicationContext applicationContext;

    @Bean(name = THREAD_APPLICATION_EVENT_MULTICASTER)
    public ApplicationEventMulticaster threadApplicationEventMulticaster() {
        SimpleApplicationEventMulticaster eventMulticaster = new SimpleApplicationEventMulticaster();
        applicationContext.getApplicationListeners().forEach(eventMulticaster::addApplicationListener);
        eventMulticaster.setTaskExecutor(new ThreadPoolExecutor(5, 10, 1000, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(1000), new CustomizableThreadFactory("threadApplicationEventMulticaster")));
        return eventMulticaster;
    }
}