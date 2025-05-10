package org.example.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class EventListenerConfig {
    @EventListener
    public void demoEvent(DemoEvent event) {
        log.info("thread:{} demoEvent: {}", Thread.currentThread().getName(), event.getMsg());
    }

    @EventListener(classes = DemoEvent.class)
    public void demoEventV2(DemoEvent event) {
        log.info("thread:{} demoEventV2: {}", Thread.currentThread().getName(), event.getMsg());
    }

    // @EventListener(condition = "#event.msg.equals('applicationEventMulticaster')")
    @EventListener(condition = "!#event.msg.equals('applicationEventMulticaster')")
    public void demoEventV3(DemoEvent event) {
        log.info("thread:{} demoEventV3: {}", Thread.currentThread().getName(), event.getMsg());
    }
}
