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
        HashMap<String, Integer> shoppingCartPorductMap = new HashMap<String, Integer>();
        AtomicBoolean shoppingCartCreateStatus = new AtomicBoolean(true);
        AtomicReference<String> shoppingCartCreateMessage = new AtomicReference<>("添加成功");
        AtomicInteger shoppingCartCreateVacant = new AtomicInteger();
        User user = userMapper.selectOne(new QueryWrapper<User>().eq("id", userId));
        ShoppingCart shoppingCart = shoppingCartMapper.selectOne(new QueryWrapper<ShoppingCart>().eq("userId", user.getShoppingCart()));
        shoppingCartPorductMap.put("product1", shoppingCart.getProduct1());
        shoppingCartPorductMap.put("product2", shoppingCart.getProduct2());
        shoppingCartPorductMap.put("product3", shoppingCart.getProduct3());
        shoppingCartPorductMap.put("product4", shoppingCart.getProduct4());
        shoppingCartPorductMap.entrySet().stream().forEach(entry -> {
            if (entry.getValue() != 0) {
                shoppingCartCreateVacant.incrementAndGet();
            }
            if (entry.getValue().equals(productId)) {
                shoppingCartCreateStatus.set(false);
                shoppingCartCreateMessage.set("购物车已存在");
                return;
            }
        });
        if (shoppingCartCreateStatus.get() == false || shoppingCartCreateVacant.get() == 4) {
            return json.toJson(new ShoppingCartResponse<String>(
                    BaseResponse.ERROR_CODE, BaseResponse.ERROR_MESSAGE,
                    shoppingCartCreateStatus.get() == false ? shoppingCartCreateMessage.get() : "最多有4件商品"
            ));
        }
        shoppingCartPorductMap.entrySet().stream().forEach(entry -> {
            if (entry.getValue() == 0) {
                entry.setValue(productId);
            }
        });
        shoppingCart.setProduct1(shoppingCartPorductMap.get("product1"));
        shoppingCart.setProduct2(shoppingCartPorductMap.get("product2"));
        shoppingCart.setProduct3(shoppingCartPorductMap.get("product3"));
        shoppingCart.setProduct4(shoppingCartPorductMap.get("product4"));
        shoppingCartMapper.updateById(shoppingCart);
        return json.toJson(new ShoppingCartResponse<String>(
                BaseResponse.SUCCESS_CODE, BaseResponse.SUCCESS_MESSAGE, "添加成功"
        ));
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
}
