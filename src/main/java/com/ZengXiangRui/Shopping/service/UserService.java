package com.ZengXiangRui.Shopping.service;

import com.ZengXiangRui.Shopping.entity.database.User;
import com.baomidou.mybatisplus.extension.service.IService;

public interface UserService extends IService<User> {
    String login(String username);
}
