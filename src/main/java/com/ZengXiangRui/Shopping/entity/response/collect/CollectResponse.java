package com.LiZhuang.Shopping.entity.response.collect;

import com.LiZhuang.Shopping.entity.response.BaseResponse;

public class CollectResponse <T> extends BaseResponse <T>{
    public CollectResponse(int code, String message, T data) {
        super(code, message, data);
    }
}
