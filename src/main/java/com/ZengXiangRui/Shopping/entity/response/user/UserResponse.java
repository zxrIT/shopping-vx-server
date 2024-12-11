package com.ZengXiangRui.Shopping.entity.response.user;

import com.ZengXiangRui.Shopping.entity.response.BaseResponse;

public class UserResponse<T> extends BaseResponse<T> {
    public UserResponse(int code, String message, T data) {
        super(code, message, data);
    }
}
