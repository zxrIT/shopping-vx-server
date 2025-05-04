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

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

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

    @Override
    public String addShoppingCart(String userId, int productId) {
        try {
            // 获取用户的购物车
            User user = userMapper.selectOne(new QueryWrapper<User>().eq("id", userId));
            ShoppingCart shoppingCart = shoppingCartMapper.selectOne(
                new QueryWrapper<ShoppingCart>().eq("userId", user.getShoppingCart())
            );
            
            // 检查商品是否已在购物车中
            if (productId == shoppingCart.getProduct1() ||
                productId == shoppingCart.getProduct2() ||
                productId == shoppingCart.getProduct3() ||
                productId == shoppingCart.getProduct4()) {
                return json.toJson(new ShoppingCartResponse<String>(
                    BaseResponse.ERROR_CODE,
                    BaseResponse.ERROR_MESSAGE,
                    "商品已在购物车中"
                ));
            }
            
            // 找到第一个空位置（值为0的位置）
            if (shoppingCart.getProduct1() == 0) {
                shoppingCart.setProduct1(productId);
            } else if (shoppingCart.getProduct2() == 0) {
                shoppingCart.setProduct2(productId);
            } else if (shoppingCart.getProduct3() == 0) {
                shoppingCart.setProduct3(productId);
            } else if (shoppingCart.getProduct4() == 0) {
                shoppingCart.setProduct4(productId);
            } else {
                return json.toJson(new ShoppingCartResponse<String>(
                    BaseResponse.ERROR_CODE,
                    BaseResponse.ERROR_MESSAGE,
                    "购物车已满，最多添加4件商品"
                ));
            }
            
            // 更新购物车
            int result = shoppingCartMapper.updateById(shoppingCart);
            if (result > 0) {
                return json.toJson(new ShoppingCartResponse<String>(
                    BaseResponse.SUCCESS_CODE,
                    BaseResponse.SUCCESS_MESSAGE,
                    "添加成功"
                ));
            } else {
                return json.toJson(new ShoppingCartResponse<String>(
                    BaseResponse.ERROR_CODE,
                    BaseResponse.ERROR_MESSAGE,
                    "添加失败"
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return json.toJson(new ShoppingCartResponse<String>(
                BaseResponse.ERROR_CODE,
                BaseResponse.ERROR_MESSAGE,
                "添加失败：" + e.getMessage()
            ));
        }
    }

    @Override
    public String deleteShoppingCart(String userId, int productId) {
        User user = userMapper.selectOne(new QueryWrapper<User>().eq("id", userId));
        ShoppingCart shoppingCart = shoppingCartMapper.selectOne(new QueryWrapper<ShoppingCart>().eq("userId", user.getShoppingCart()));
        if (shoppingCart.getProduct1().equals(productId)) {
            shoppingCart.setProduct1(0);
        } else if (shoppingCart.getProduct2().equals(productId)) {
            shoppingCart.setProduct2(0);
        } else if (shoppingCart.getProduct3().equals(productId)) {
            shoppingCart.setProduct3(0);
        } else if (shoppingCart.getProduct4().equals(productId)) {
            shoppingCart.setProduct4(0);
        }
        shoppingCartMapper.updateById(shoppingCart);
        return json.toJson(new ShoppingCartResponse<String>(
                BaseResponse.SUCCESS_CODE, BaseResponse.SUCCESS_MESSAGE, "删除成功"
        ));
    }

    @Override
    public String clearShoppingCart(String userId) {
        try {
            // 获取用户的购物车
            User user = userMapper.selectOne(new QueryWrapper<User>().eq("id", userId));
            ShoppingCart shoppingCart = shoppingCartMapper.selectOne(
                new QueryWrapper<ShoppingCart>().eq("userId", user.getShoppingCart())
            );
            
            // 清空所有商品
            shoppingCart.setProduct1(0);
            shoppingCart.setProduct2(0);
            shoppingCart.setProduct3(0);
            shoppingCart.setProduct4(0);
            
            // 更新购物车
            int result = shoppingCartMapper.updateById(shoppingCart);
            
            if (result > 0) {
                return json.toJson(new ShoppingCartResponse<String>(
                        BaseResponse.SUCCESS_CODE,
                        BaseResponse.SUCCESS_MESSAGE,
                        "购物车已清空"
                ));
            } else {
                return json.toJson(new ShoppingCartResponse<String>(
                        BaseResponse.ERROR_CODE,
                        BaseResponse.ERROR_MESSAGE,
                        "清空购物车失败"
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return json.toJson(new ShoppingCartResponse<String>(
                    BaseResponse.ERROR_CODE,
                    BaseResponse.ERROR_MESSAGE,
                    "清空购物车失败：" + e.getMessage()
            ));
        }
    }
}
