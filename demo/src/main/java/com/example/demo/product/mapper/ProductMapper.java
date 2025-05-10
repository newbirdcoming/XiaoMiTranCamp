package com.example.demo.product.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.product.domain.Product;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProductMapper extends BaseMapper<Product> {
    public Product queryStock(int id);
}
