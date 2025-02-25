package com.ZengXiangRui.Shopping.controller;

import com.ZengXiangRui.Shopping.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@SuppressWarnings("all")
@RequestMapping("/product")
@CrossOrigin
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/getHotProduct")
    private String getHotProduct() {
        return productService.getHotProduct();
    }

    @GetMapping("/admin/getProduct/success")
    private String getProductSuccess() {
        return productService.getAdminProductSuccess();
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
