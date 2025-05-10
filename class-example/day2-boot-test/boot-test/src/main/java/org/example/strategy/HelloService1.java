package org.example.strategy;

import org.springframework.stereotype.Service;

@Service
public class HelloService1 implements HelloService {
    @Override
    public String getType() {
        return "Hello";
    }

    @Override
    public void sayHello(HelloServiceBean serviceBean) {
        serviceBean.setXxx("HelloService1");
    }
}
