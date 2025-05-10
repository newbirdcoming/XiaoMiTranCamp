package org.example.strategy;

import org.springframework.stereotype.Service;

@Service
public class HelloService2 implements HelloService {
    @Override
    public String getType() {
        return "Hello2";
    }

    @Override
    public void sayHello(HelloServiceBean serviceBean) {
        serviceBean.setXxx("HelloService2");
    }
}
