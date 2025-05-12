package com.example.day3classwork.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.day3classwork.domain.Product;
import com.example.day3classwork.service.ProductService;
import com.example.day3classwork.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* @author 13789
* @description 针对表【product】的数据库操作Service实现
* @createDate 2025-05-11 15:47:56
*/
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product>
    implements ProductService{


    @Autowired
    private ProductMapper productMapper;
    @Override
    public String reducestock(int id, int number) {
        Product product = productMapper.selectById(id);
        if (product==null)
            return "商品不存在";
        else if(product.getProductStock()<number) {
            return "商品库存不足";
        }
        else {
            product.setProductStock(product.getProductStock()-number);
            productMapper.updateById(product);
            return "商品库存减少成功";
        }
    }
}




