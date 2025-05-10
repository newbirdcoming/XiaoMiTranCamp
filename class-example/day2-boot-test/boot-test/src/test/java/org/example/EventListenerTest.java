package org.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.example.event.DemoEvent;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.ApplicationEventMulticaster;

@SpringBootTest(classes = App.class)
public class EventListenerTest {
    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private ApplicationEventMulticaster applicationEventMulticaster;

    @Autowired(required = false)
    private ApplicationEventMulticaster threadApplicationEventMulticaster;

    @Test
    public void test() throws JsonProcessingException {
        // System.out.println(applicationContext.getEnvironment().getProperty("spring.application.name"));
        applicationContext.publishEvent(new DemoEvent(this, "applicationEventMulticaster", applicationContext));
    }

    @Test
    public void testV2() throws JsonProcessingException {
        applicationEventMulticaster.multicastEvent(new DemoEvent(this, "applicationEventMulticaster", applicationContext));
        threadApplicationEventMulticaster.multicastEvent(new DemoEvent(this, "threadApplicationEventMulticaster", applicationContext));
    }
}
