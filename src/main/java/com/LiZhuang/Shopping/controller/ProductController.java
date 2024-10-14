package com.LiZhuang.Shopping.controller;

import com.LiZhuang.Shopping.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SuppressWarnings("all")
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/getHotProduct")
    private String getHotProduct() {
        return productService.getHotProduct();
    }

    @GetMapping("/getOtherProduct")
    private String getOtherProduct() {
        return productService.getOtherProduct();
    }
}