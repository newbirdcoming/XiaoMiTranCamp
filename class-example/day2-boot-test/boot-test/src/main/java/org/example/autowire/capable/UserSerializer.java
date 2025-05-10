package org.example.autowire.capable;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.example.di.Parent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;

public class UserSerializer extends StdSerializer<User> {
    @Value("${spring.application.name}")
    private String appName;

    @Autowired
    private Parent parentOne;

    // 提供空构造是为了可以使用注解方式，一般建议提供
    public UserSerializer() {
        this(User.class);
    }

    protected UserSerializer(Class<User> t) {
        super(t);
    }

    @Override
    public void serialize(User user, JsonGenerator gen, SerializerProvider serializerProvider) throws IOException {
        System.out.println("转换器PersonSerializer执行serialize()方法...");
        gen.writeStartObject();
        gen.writeStringField("jsonStr", user.getName() + ":" + appName + ":" + parentOne.getName());
        gen.writeEndObject();
    }
}