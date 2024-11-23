package com.LiZhuang.Shopping.entity.response.address;

import com.LiZhuang.Shopping.entity.response.BaseResponse;

public class AddressResponse<T> extends BaseResponse<T> {
    public AddressResponse(int code, String message, T data) {
        super(code, message, data);
    }
}
