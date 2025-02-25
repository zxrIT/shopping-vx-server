package com.ZengXiangRui.Shopping.entity.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BaseResponse<T> {
    public static final int SUCCESS_CODE = 200;
    public static final int ERROR_CODE = 500;
    public static final int FORBIDDEN_CODE = 403;
    public static final String SUCCESS_MESSAGE = "success";
    public static final String ERROR_MESSAGE = "error";
    public static final String FORBIDDEN_MESSAGE = "forbidden";

    private int code;
    private String message;
    private T data;
}
