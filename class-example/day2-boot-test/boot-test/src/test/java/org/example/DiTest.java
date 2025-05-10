package org.example;

import org.example.di.GenericBean;
import org.example.di.Parent;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@SpringBootTest(classes = App.class)
public class DiTest {
    // 使用@Autowired注入，测试一下：
    @Autowired
    private GenericBean<Object, Object> objectGenericBean;
    @Autowired
    private GenericBean<String, String> stringGenericBean;
    // 注意，容器里虽然有两个Parent，这里即使不使用@Qualifier也不会报错。
// 但是需要注意字段名parentOne，必须是容器里存在的，否则就报错了。
    @Autowired
    private Parent parentOne;

    // Spring4.0后的新特性,这样会注入所有类型为(包括子类)GenericBean的Bean(但是顺序是不确定的,可通过Order接口控制顺序)
    @Autowired
    private List<GenericBean> genericBeans;
    // 这里的key必须是String类型，把GenericBean类型的都拿出来了，beanName->Bean
    @Autowired
    private Map<String, GenericBean> genericBeanMap;
    // 这里面，用上泛型也是好使的，就只会拿指定泛型的了
    @Autowired
    private Map<String, GenericBean<Object, Object>> genericBeanObjMap;

    @Autowired
    private Optional<List<Parent>> parents;

    @Autowired
    private Optional<DemoService> demoService;

    @Test
    public void test() {
        System.out.println("di test");
    }
}
