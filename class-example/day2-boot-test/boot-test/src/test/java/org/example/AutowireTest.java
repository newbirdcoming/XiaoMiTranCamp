package org.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.autowire.capable.User;
import org.example.domain.Child;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

// @ExtendWith(SpringExtension.class)
// @RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
public class AutowireTest {
    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private ObjectMapper om;

    @Test
    public void test() {
        // ApplicationContext里面持久AutowireCapableBeanFactory这个工具的，它真实的实现类一般都是：DefaultListableBeanFactory
        AutowireCapableBeanFactory autowireCapableBeanFactory = applicationContext.getAutowireCapableBeanFactory();

        // 我们把Child的创建过程都交给Bean工厂去帮我们处理，自己连new都不需要了 （createBean方法执行多次，就会创建多个child实例）
        Child child = (Child) autowireCapableBeanFactory.createBean(Child.class, AutowireCapableBeanFactory.AUTOWIRE_BY_TYPE, false);

        // 简直残暴，没有@Autowired注解都给注入进来了
        System.out.println(child.getParentOne());

        // 抛出异常 No qualifying bean of type 'com.fsx.bean.Child' available
        // 能佐证：我们的Bean并没交给Spring容器管理，它只是帮我们创建好了，并把对应属性注入进去了
        Child bean = applicationContext.getBean(Child.class);
        System.out.println(bean);
    }

    @Test
    public void test2() throws JsonProcessingException {
        User user = new User();
        user.setName("Tom");
        // System.out.println(new ObjectMapper().writeValueAsString(user));
        System.out.println(om.writeValueAsString(user));
    }
}