package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * Hello world!
 */
// @EnableRetry
@SpringBootApplication
@EnableAspectJAutoProxy
public class App {
    public static void main(String[] args) {
        ConfigurableApplicationContext app = SpringApplication.run(App.class, args);
    }
}
