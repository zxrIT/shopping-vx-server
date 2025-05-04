package com.ZengXiangRui.Shopping.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    private JsonUtil() {
    }

    public static String objectToJson(Object object) throws RuntimeException {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException jsonProcessingException) {
            throw new RuntimeException(jsonProcessingException.getMessage());
        }
    }

    public static <T> T jsonToObject(String json, Class<T> clazz) throws RuntimeException {
        try {
            return objectMapper.readValue(json, clazz);
        } catch (JsonProcessingException jsonProcessingException) {
            throw new RuntimeException(jsonProcessingException.getMessage());
        }
    }
}
