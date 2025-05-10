package com.example.product.service.serviceImpl;



import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.product.entity.Product;
import com.example.product.mapper.ProductMapper;
import com.example.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {


    @Autowired
    private ProductMapper productMapper;
    @Override
    public String add(Product product) {
        productMapper.calculate(product);
        int insert = productMapper.insert(product);
        if (insert>0){
            return "插入成功";
        }else{
            return "插入失败";
        }
    }

    @Override
    public String reduceStock(int id, int number) {
        Product product = productMapper.selectById(id);
//        库存不足
        if (product.getProductStock()<number){
            return "库存不足";
        }
//        扣减库存
        else{
            product.setProductStock(product.getProductStock()-number);
            int update = productMapper.updateById(product);
            if (update>0){
                return "扣减成功";
            }else{
                return "扣减失败";
            }

        }
    }
}
