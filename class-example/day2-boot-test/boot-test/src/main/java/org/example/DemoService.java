package org.example;

import lombok.extern.slf4j.Slf4j;
import org.example.strategy.HelloService;
import org.example.strategy.HelloServiceBean;
import org.example.strategy.HelloServiceManager;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

@Slf4j
// @Service
public class DemoService {
    @Autowired
    // @Resource
    private List<HelloService> helloService;

    @Autowired
    private Map<String, HelloService> helloService2Map;

    @Autowired
    private HelloServiceManager helloServiceManager;

    public String sayHello(HelloServiceBean serviceBean) {
        helloServiceManager.getService(serviceBean.getType()).ifPresent(item -> item.sayHello(serviceBean));
        // helloService.forEach(item -> {
        //     if (item.getType().equals(serviceBean.getType())) {
        //         // support***(XXX)
        //         // log.info("serviceBean {}", serviceBean);
        //         item.sayHello(serviceBean);
        //     }
        // });
        return "hello";
    }
}
