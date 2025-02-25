package com.ZengXiangRui.Shopping.service.impl;

import com.ZengXiangRui.Shopping.entity.database.Address;
import com.ZengXiangRui.Shopping.entity.database.Collect;
import com.ZengXiangRui.Shopping.entity.database.ShoppingCart;
import com.ZengXiangRui.Shopping.entity.database.User;
import com.ZengXiangRui.Shopping.entity.response.BaseResponse;
import com.ZengXiangRui.Shopping.entity.response.user.UserResponse;
import com.ZengXiangRui.Shopping.mapper.CollectMapper;
import com.ZengXiangRui.Shopping.mapper.ShoppingCartMapper;
import com.ZengXiangRui.Shopping.mapper.UserMapper;
import com.ZengXiangRui.Shopping.requestParam.AdminLoginRequestParam;
import com.ZengXiangRui.Shopping.service.UserService;
import com.ZengXiangRui.Shopping.util.json.Json;
import com.ZengXiangRui.Shopping.util.jwt.Token;
import com.ZengXiangRui.Shopping.utils.Encryption;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@SuppressWarnings("all")
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {
    private final static Logger logger =
            LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private CollectMapper collectMapper;

    @Autowired
    private ShoppingCartMapper shoppingCartMapper;

    @Autowired
    private Json json;

    @Autowired
    private Token token;

    @Override
    public String login(String username, String userIcon) {
        User user = userMapper.selectOne(new QueryWrapper<User>()
                .eq("username", username));
        if (user == null) {
            user = new User();
            Collect collect = new Collect();
            Address address = new Address();
            ShoppingCart shoppingCart = new ShoppingCart();
            String uuid = String.valueOf(UUID.randomUUID());
            user.setId(uuid);
            user.setUsername(username);
            user.setUserIcon(userIcon);
            user.setToken(token.getToken(username));
            user.setShoppingCart(uuid);
            user.setCollect(uuid);
            user.setAddress(uuid);
            collect.setId(uuid);
            address.setId(uuid);
            shoppingCart.setUserId(uuid);
            collectMapper.insert(collect);
            userMapper.insert(user);
            shoppingCartMapper.insert(shoppingCart);
        }
        return json.toJson(new UserResponse<User>(
                BaseResponse.SUCCESS_CODE,
                BaseResponse.SUCCESS_MESSAGE,
                user)
        );
    }

    @Override
    public String getAllUser() {
        List<User> users = userMapper.selectList(new QueryWrapper<User>());
        return json.toJson(new UserResponse<List<User>>(
                BaseResponse.SUCCESS_CODE,
                BaseResponse.SUCCESS_MESSAGE,
                users
        ));
    }

    @Override
    public String adminLogin(AdminLoginRequestParam adminLoginRequestParam) {
        System.out.println(Encryption.encryptToMd5(adminLoginRequestParam.getPassword()));
        User user = userMapper.selectOne(new LambdaQueryWrapper<User>().eq(
                User::getAccount, adminLoginRequestParam.getUsername()
        ));
        if (user == null) {
            return json.toJson(new UserResponse<String>(
                    BaseResponse.FORBIDDEN_CODE, BaseResponse.FORBIDDEN_MESSAGE, "暂无此账号请联系管理员为您开通"
            ));
        }
        if (!user.getPassword().equals(Encryption.encryptToMd5(adminLoginRequestParam.getPassword()))) {
            return json.toJson(new UserResponse<String>(
                    BaseResponse.FORBIDDEN_CODE, BaseResponse.FORBIDDEN_MESSAGE, "账号密码不正确请重试"
            ));
        }
        return json.toJson(new UserResponse<User>(
                BaseResponse.SUCCESS_CODE, BaseResponse.SUCCESS_MESSAGE, user
        ));
    }
}
