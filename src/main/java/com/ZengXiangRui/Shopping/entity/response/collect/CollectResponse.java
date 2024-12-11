package com.ZengXiangRui.Shopping.entity.response.collect;

import com.ZengXiangRui.Shopping.entity.response.BaseResponse;

public class CollectResponse <T> extends BaseResponse <T>{
    public CollectResponse(int code, String message, T data) {
        super(code, message, data);
    }
}
