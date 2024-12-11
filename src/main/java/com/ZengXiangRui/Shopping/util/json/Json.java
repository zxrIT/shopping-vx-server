package com.ZengXiangRui.Shopping.util.json;

import com.google.gson.Gson;
import org.springframework.stereotype.Component;

@Component
public class Json {
    private static final Gson gson = new Gson();

    public static String toJson(Object obj) {
        return gson.toJson(obj);
    }
}
