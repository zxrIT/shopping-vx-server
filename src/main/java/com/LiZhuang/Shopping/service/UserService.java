package com.LiZhuang.Shopping.service;

import com.LiZhuang.Shopping.entity.database.User;
import com.baomidou.mybatisplus.extension.service.IService;

public interface UserService extends IService<User> {
    String login(String username);
}
