/*
 * Copyright 2013-2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.day3classwork.controller;

import com.example.day3classwork.domain.Product;
import com.example.day3classwork.domain.Result;
import com.example.day3classwork.service.ProductService;
import com.sun.istack.internal.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * @author <a href="mailto:chenxilzx1@gmail.com">theonefx</a>
 */
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/add")
    public Result<String> add(@RequestBody Product product) {
        boolean success = productService.save(product);
        return success ?
                Result.success("添加成功") :
                Result.error("500", "添加失败");
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> get(@PathVariable Long id) {
        try {
            Product product = productService.getById(id);
            return ResponseEntity.of(Optional.ofNullable(product));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("查询失败");
        }
    }

    @PostMapping("/reducestock")
    public Result<String> reduceStock(
            @RequestParam @NotNull int id,
            @RequestParam(required = true,defaultValue = "1")  int number ) {
        try {
            String result = productService.reducestock(id, number);
            return Result.success(result);
        } catch (Exception e) {
            return Result.error("400", "库存不足");
        }
    }
}
