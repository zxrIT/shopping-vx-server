package com.ZengXiangRui.Shopping.controller;

import com.ZengXiangRui.Shopping.requestParam.AdminLoginRequestParam;
import com.ZengXiangRui.Shopping.requestParam.UserRequestParam;
import com.ZengXiangRui.Shopping.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@SuppressWarnings("all")
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/admin/login")
    private String adminLogin(@RequestBody AdminLoginRequestParam adminLoginRequestParam) {
        return userService.adminLogin(adminLoginRequestParam);
    }

    @GetMapping("/get/allUser")
    private String getAllUser() {
        return userService.getAllUser();
    }

    @PostMapping("/login")
    public String login(@RequestBody UserRequestParam userRequestParam) {
        return userService.login(userRequestParam.getUsername(), userRequestParam.getAvatarUrl());
    }
}
