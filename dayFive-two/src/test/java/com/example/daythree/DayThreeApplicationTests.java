package com.example.daythree;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DayThreeApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    public void test() {
        Thread.currentThread().interrupt();
    }

}
