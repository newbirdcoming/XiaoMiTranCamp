package org.example.properties;

import com.sun.xml.internal.fastinfoset.sax.Features;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "app")
public class AppConfig {
    private String name;
    private Boolean version;
    private String env;
    private Features features;
    public static class Features {
        private boolean enabled;
        private Cache cache; // 另一个嵌套类，用于封装 cache 配置

        // getter 和 setter 方法
        public boolean isEnabled() { return enabled; }
        public void setEnabled(boolean enabled) { this.enabled = enabled; }

        public Cache getCache() { return cache; }
        public void setCache(Cache cache) { this.cache = cache; }

        // 静态内部类：Cache
        public static class Cache {
            private int ttl;

            // getter 和 setter
            public int getTtl() { return ttl; }
            public void setTtl(int ttl) { this.ttl = ttl; }
        }
    }
}