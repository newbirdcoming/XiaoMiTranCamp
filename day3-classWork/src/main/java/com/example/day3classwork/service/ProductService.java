package com.example.day3classwork.service;

import com.example.day3classwork.domain.Product;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author 13789
* @description 针对表【product】的数据库操作Service
* @createDate 2025-05-11 15:47:56
*/
public interface ProductService extends IService<Product> {

    String reducestock(int id, int number);
}
