package com.ZengXiangRui.Shopping.service;

import com.ZengXiangRui.Shopping.entity.database.ShoppingCart;
import com.baomidou.mybatisplus.extension.service.IService;

public interface ShoppingCartService extends IService<ShoppingCart> {
    String getAll(String userId);

    String addShoppingCart(String userId, int productId);

    String deleteShoppingCart(String userId, int productId);

    String clearShoppingCart(String userId);
}
