package com.example.day3classwork.mapper;

import com.example.day3classwork.domain.Product;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 13789
* @description 针对表【product】的数据库操作Mapper
* @createDate 2025-05-11 15:47:56
* @Entity com.example.day3classwork.domain.Product
*/
@Mapper
public interface ProductMapper extends BaseMapper<Product> {

}




