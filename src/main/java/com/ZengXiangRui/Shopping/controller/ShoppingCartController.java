package com.ZengXiangRui.Shopping.controller;

import com.ZengXiangRui.Shopping.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SuppressWarnings("all")
@RequestMapping("/shoppingCart")
public class ShoppingCartController {
    @Autowired
    private ShoppingCartService shoppingCartService;

    @GetMapping("/getAll/{userId}")
    public String getAll(@PathVariable String userId) {
        return shoppingCartService.getAll(userId);
    }

    @GetMapping("/addShoppingCartProduct/{userId}/{productId}")
    public String addShoppingCartProduct(@PathVariable String userId, @PathVariable int productId) {
        return shoppingCartService.addShoppingCart(userId, productId);
    }

    @GetMapping("/deleteShoppingCartProduct/{userId}/{productId}")
    public String deleteShoppingCartProduct(@PathVariable String userId, @PathVariable int productId) {
        return shoppingCartService.deleteShoppingCart(userId, productId);
    }
}
