package com.LiZhuang.Shopping.service;

import com.LiZhuang.Shopping.entity.database.Product;
import com.baomidou.mybatisplus.extension.service.IService;

public interface ProductService extends IService<Product> {
    String getHotProduct();
    String getOtherProduct();
    String getProductType();
    String getProduct(int typeId);
    String getProductDetails(int id);
}