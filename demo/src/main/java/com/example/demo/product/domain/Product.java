package com.example.demo.product.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
@TableName(value ="product")
public class Product {
    private int id;
    private String name;
    private int stock;
    private double price;
}
