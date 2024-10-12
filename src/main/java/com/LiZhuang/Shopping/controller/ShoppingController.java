package com.LiZhuang.Shopping.controller;

import com.LiZhuang.Shopping.service.ShoppingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SuppressWarnings("all")
public class ShoppingController {
    @Autowired
    private ShoppingService shoppingService;

}
