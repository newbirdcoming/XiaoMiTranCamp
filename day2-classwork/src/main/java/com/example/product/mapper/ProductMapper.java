package com.example.product.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.product.entity.Product;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface ProductMapper extends BaseMapper<Product> {
    List<Product> selectAll();
}
