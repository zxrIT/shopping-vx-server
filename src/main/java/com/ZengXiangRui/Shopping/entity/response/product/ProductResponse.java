package com.ZengXiangRui.Shopping.entity.response.product;

import com.ZengXiangRui.Shopping.entity.response.BaseResponse;

public class ProductResponse<T> extends BaseResponse<T> {
    public ProductResponse(int code, String message, T data) {
        super(code, message, data);
    }
}
