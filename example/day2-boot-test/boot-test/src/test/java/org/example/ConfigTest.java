package org.example;


import org.example.properties.AppConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@SpringBootTest
public class ConfigTest {

    @Autowired
    private AppConfig appConfig;

    @Test
    void testAppConfigProperties() {
        // 基本属性测试
        assertEquals("MyApp", appConfig.getName());
        assertEquals("1.0.0", appConfig.getVersion());
        assertEquals("dev", appConfig.getEnv());

        assertTrue(appConfig.getFeatures().isEnabled());
        assertEquals(3600, appConfig.getFeatures().getCache().getTtl());
    }
}
