package com.example.product.controller;




import com.example.product.entity.Product;
import com.example.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController


@RequestMapping("/product")
//http://localhost:8080/product
public class productController {

    @Autowired
    private ProductService service;

//    商品添加
    @GetMapping("/add")
    public String add(Product product) {
        return service.add(product);
    }


//    商品查询
    @GetMapping("/querystock")
    public String query(int id) {
        Product byId = service.getById(id);
//        不存在
        if (byId == null) {
            return "商品不存在";
        }
        else{
            return "商品库存为"+byId.getProductStock();
        }
    }


//    库存扣减
    @RequestMapping("/reduce")
    public String reduce(int id,int number) {
//        修改扣减库存
            return service.reduceStock(id,number);
    }




}
