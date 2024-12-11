package com.ZengXiangRui.Shopping.entity.response.shoppingCart;

import com.ZengXiangRui.Shopping.entity.response.BaseResponse;

public class ShoppingCartResponse<T> extends BaseResponse<T> {
    public ShoppingCartResponse(int code, String message, T data) {
        super(code, message, data);
    }
}
