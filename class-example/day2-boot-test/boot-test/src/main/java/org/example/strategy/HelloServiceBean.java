package org.example.strategy;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Getter
@Setter
@ToString
@Accessors(chain = true)
public class HelloServiceBean {
    private String type;
    private String xxx;
}
