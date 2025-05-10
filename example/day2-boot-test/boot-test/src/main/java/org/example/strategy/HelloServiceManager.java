package org.example.strategy;

import org.springframework.stereotype.Service;

@Service
public class HelloServiceManager extends BaseDynamicMultiManager<String, HelloService> {
    @Override
    protected String getKey(HelloService helloService) {
        return helloService.getType();
    }
}
