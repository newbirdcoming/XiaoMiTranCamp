package com.example.product.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.example.product.entity.Product;

public interface ProductService extends IService<Product> {

    String add(Product product);

    String reduceStock(int id,int number);
}
