package com.LiZhuang.Shopping.service.impl;

import com.LiZhuang.Shopping.entity.response.Response;
import com.LiZhuang.Shopping.service.ShoppingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@SuppressWarnings("all")
public class ShoppingServiceImpl implements ShoppingService {
    @Autowired
    private Response response;
}
