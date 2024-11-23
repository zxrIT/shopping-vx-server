package com.LiZhuang.Shopping.entity.response.user;

import com.LiZhuang.Shopping.entity.response.BaseResponse;

public class UserResponse<T> extends BaseResponse<T> {
    public UserResponse(int code, String message, T data) {
        super(code, message, data);
    }
}
