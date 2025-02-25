package com.ZengXiangRui.Shopping.service;

import com.ZengXiangRui.Shopping.entity.database.User;
import com.ZengXiangRui.Shopping.requestParam.AdminLoginRequestParam;
import com.baomidou.mybatisplus.extension.service.IService;

public interface UserService extends IService<User> {
    String login(String username, String userIcon);

    String getAllUser();

    String adminLogin(AdminLoginRequestParam adminLoginRequestParam);
}
