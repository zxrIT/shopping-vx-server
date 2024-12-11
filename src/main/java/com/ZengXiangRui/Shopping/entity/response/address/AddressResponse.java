package com.ZengXiangRui.Shopping.entity.response.address;

import com.ZengXiangRui.Shopping.entity.response.BaseResponse;

public class AddressResponse<T> extends BaseResponse<T> {
    public AddressResponse(int code, String message, T data) {
        super(code, message, data);
    }
}
