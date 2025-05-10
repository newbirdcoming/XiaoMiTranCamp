package org.example.di;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DiConfig {
    // config配置文件中注入两个泛型Bean
    @Bean
    public Parent parentOne() {
        return new Parent("ParnetOne");
    }

    @Bean
    public Parent parentTwo() {
        return new Parent("parentTwo");
    }

    @Bean
    public GenericBean<String, String> stringGeneric() {
        return new GenericBean<String, String>("str1", "str2");
    }

    @Bean
    public GenericBean<Object, Object> objectGeneric() {
        return new GenericBean<Object, Object>("obj1", 2);
    }
}
