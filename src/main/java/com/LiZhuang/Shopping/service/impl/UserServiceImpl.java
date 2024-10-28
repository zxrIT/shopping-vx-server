package com.LiZhuang.Shopping.service.impl;

import com.LiZhuang.Shopping.entity.database.Address;
import com.LiZhuang.Shopping.entity.database.Collect;
import com.LiZhuang.Shopping.entity.database.User;
import com.LiZhuang.Shopping.entity.response.BaseResponse;
import com.LiZhuang.Shopping.entity.response.user.UserResponse;
import com.LiZhuang.Shopping.mapper.CollectMapper;
import com.LiZhuang.Shopping.mapper.UserMapper;
import com.LiZhuang.Shopping.service.UserService;
import com.LiZhuang.Shopping.util.json.Json;
import com.LiZhuang.Shopping.util.jwt.Token;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    private Json json;

    @Autowired
    private Token token;

    @Override
    public String login(String username) {
        User user = userMapper.selectOne(new QueryWrapper<User>()
                .eq("username", username));
        if (user == null) {
            user = new User();
            Collect collect = new Collect();
            Address address = new Address();
            String uuid = String.valueOf(UUID.randomUUID());
            user.setId(uuid);
            user.setUsername(username);
            user.setToken(token.getToken(username));
            user.setShoppingCart(uuid);
            user.setCollect(uuid);
            user.setAddress(uuid);
            collect.setId(uuid);
            address.setId(uuid);
            collectMapper.insert(collect);
            userMapper.insert(user);
        }
        return json.toJson(new UserResponse<User>(
                BaseResponse.SUCCESS_CODE,
                BaseResponse.SUCCESS_MESSAGE,
                user)
        );
    }
}
