package com.example.demo.product.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.product.domain.Product;
import com.example.demo.product.service.ProductService;
import com.example.demo.product.mapper.ProductMapper;
import org.springframework.stereotype.Service;

/**
* @author 13789
* @description 针对表【product】的数据库操作Service实现
* @createDate 2025-05-11 15:04:53
*/
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product>
    implements ProductService{

}




