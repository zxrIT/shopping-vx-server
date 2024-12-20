package com.ZengXiangRui.Shopping.controller;

import com.ZengXiangRui.Shopping.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("/getProductType")
    private String getProductType() {
        return productService.getProductType();
    }

    @GetMapping("/getProduct/{id}")
    private String getProduct(@PathVariable int id) {
        return productService.getProduct(id);
    }

    @GetMapping("/getProductDetails/{id}")
    private String getProductDetails(@PathVariable int id) {
        return productService.getProductDetails(id);
    }
}
