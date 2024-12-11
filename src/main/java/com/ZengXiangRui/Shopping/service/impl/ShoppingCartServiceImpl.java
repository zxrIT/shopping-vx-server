package com.ZengXiangRui.Shopping.service.impl;

import com.ZengXiangRui.Shopping.entity.database.ShoppingCart;
import com.ZengXiangRui.Shopping.entity.database.User;
import com.ZengXiangRui.Shopping.entity.response.BaseResponse;
import com.ZengXiangRui.Shopping.entity.response.shoppingCart.ShoppingCartResponse;
import com.ZengXiangRui.Shopping.mapper.ShoppingCartMapper;
import com.ZengXiangRui.Shopping.mapper.UserMapper;
import com.ZengXiangRui.Shopping.service.ShoppingCartService;
import com.ZengXiangRui.Shopping.util.json.Json;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@SuppressWarnings("all")
@Transactional
public class ShoppingCartServiceImpl extends ServiceImpl<ShoppingCartMapper, ShoppingCart>
        implements ShoppingCartService {
    @Autowired
    private ShoppingCartMapper shoppingCartMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private Json json;

    @Override
    public String getAll(String userId) {
        User user = userMapper.selectOne(new QueryWrapper<User>().eq("id", userId));
        ShoppingCart shoppingCart = shoppingCartMapper.selectOne(new QueryWrapper<ShoppingCart>().eq("userId", user.getShoppingCart()));
        return json.toJson(new ShoppingCartResponse<ShoppingCart>(
                BaseResponse.SUCCESS_CODE, BaseResponse.SUCCESS_MESSAGE, shoppingCart
        ));
    }
}
