package org.example.di;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

// 准备一个带泛型的Bean
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class GenericBean<S, T> {
    private S s;
    private T t;
}