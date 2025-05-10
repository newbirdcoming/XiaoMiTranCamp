package org.example.event;

import lombok.Getter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;

@Getter
public class DemoEvent extends ApplicationEvent {
    // 事件发布最终也是通过ApplicationContext来完成的，可以直接将其作为属性封装在事件中
    private final ApplicationContext applicationContext;
    private String msg;

    public DemoEvent(Object source, String msg, ApplicationContext context) {
        super(source);
        this.msg = msg;
        this.applicationContext = context;
    }
}
