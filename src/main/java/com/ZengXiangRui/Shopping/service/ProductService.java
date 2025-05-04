package com.ZengXiangRui.Shopping.service;

import com.ZengXiangRui.Shopping.entity.database.Product;
import com.baomidou.mybatisplus.extension.service.IService;

public interface ProductService extends IService<Product> {
    String getHotProduct();
    String getOtherProduct();
    String getProductType();
    String getProduct(int typeId);
    String getProductDetails(int id);
    String getAdminProductSuccess();
    String updateProduct(Product product);
    String deleteProduct(int id);
    String addProduct(Product product);
}
