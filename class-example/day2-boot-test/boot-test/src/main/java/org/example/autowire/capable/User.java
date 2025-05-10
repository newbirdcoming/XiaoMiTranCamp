package org.example.autowire.capable;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

@JsonSerialize(using = UserSerializer.class)
@Data
public class User {
    private Long id;
    private String name;
    private Integer age;
}