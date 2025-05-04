package com.ZengXiangRui.Shopping.entity.response.order;

import com.ZengXiangRui.Shopping.entity.response.BaseResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class OrderResponse<T> extends BaseResponse<T> {
    public OrderResponse(Integer code, String message, T data) {
        super(code, message, data);
    }
} 