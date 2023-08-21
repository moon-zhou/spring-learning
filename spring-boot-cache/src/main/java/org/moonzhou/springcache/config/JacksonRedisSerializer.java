package org.moonzhou.springcache.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import java.lang.reflect.Type;

public class JacksonRedisSerializer<T> implements RedisSerializer<T> {
    private final Type type;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public JacksonRedisSerializer(Type type) {
        this.type = type;
    }

    @Override
    public byte[] serialize(T t) throws SerializationException {
        try {
            return objectMapper.writeValueAsBytes(t);
        } catch (JsonProcessingException e) {
            throw new SerializationException("serialize fail", e);
        }
    }

    @Override
    public T deserialize(byte[] bytes) throws SerializationException {
        try {
            JavaType javaType = objectMapper.constructType(type);
            return objectMapper.readValue(bytes, javaType);
        } catch (Exception e) {
            throw new SerializationException("deserialize by type fail", e);
        }
    }
}