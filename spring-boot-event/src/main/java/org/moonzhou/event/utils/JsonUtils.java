package org.moonzhou.event.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

/**
 * @author moon zhou
 * @version 1.0
 * @description: json util, encapsulation jackson
 * @date 2022/10/6 16:00
 */
@Slf4j
public class JsonUtils {

    public static String toJson(Object object) {
        ObjectMapper objectMapper = new ObjectMapper();;
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            log.error("json str to object error: ", e);
            throw new RuntimeException(e);
        }
    }

    private static <T> T fromJson(String jsonStr, Class<T> valueType) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            // Object object = objectMapper.readValue(jsonStr,Object.class);
            return objectMapper.readValue(jsonStr, valueType);
        } catch (JsonProcessingException e) {
            log.error("json str to object error: ", e);
            throw new RuntimeException(e);
        }
    }
}
