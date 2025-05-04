package com.ZengXiangRui.Shopping.controller;

import com.ZengXiangRui.Shopping.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@SuppressWarnings("all")
@RequestMapping("/shoppingCart")
@CrossOrigin
public class ShoppingCartController {
    @Autowired
    private ShoppingCartService shoppingCartService;

    @GetMapping("/getAll/{userId}")
    public String getAll(@PathVariable String userId) {
        return shoppingCartService.getAll(userId);
    }

    @GetMapping("/addShoppingCartProduct/{userId}/{productId}")
    public String addShoppingCartProduct(@PathVariable String userId, @PathVariable int productId) {
        System.out.println(1);
        return shoppingCartService.addShoppingCart(userId, productId);
    }

    @GetMapping("/deleteShoppingCartProduct/{userId}/{productId}")
    public String deleteShoppingCartProduct(@PathVariable String userId, @PathVariable int productId) {
        return shoppingCartService.deleteShoppingCart(userId, productId);
    }

    @DeleteMapping("/clearShoppingCart/{userId}")
    public String clearShoppingCart(@PathVariable String userId) {
        return shoppingCartService.clearShoppingCart(userId);
    }
}
