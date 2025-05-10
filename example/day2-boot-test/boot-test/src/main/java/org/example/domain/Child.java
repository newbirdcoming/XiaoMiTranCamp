package org.example.domain;

import lombok.Getter;
import lombok.Setter;
import org.example.di.Parent;

@Getter
@Setter
public class Child {

    // 注意：这里并没有@Autowired注解的
    private Parent parentOne;

    private String name;
    private Integer age;

}