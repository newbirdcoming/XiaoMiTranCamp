package com.example.product.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class  Product {
//    商品id

    private Integer id;
//    商品名称
    @TableField("product_name")
    private String productName;
//    商品库存
    @TableField("product_stock")
    private Integer productStock;

}
