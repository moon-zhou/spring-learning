package org.moonzhou.springcache.config;

import com.alibaba.fastjson2.JSON;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import java.lang.reflect.Type;

public class FastJsonRedisSerializer<T> implements RedisSerializer<T> {
    private final Type type;

    public FastJsonRedisSerializer(Type type) {
        this.type = type;
    }

    @Override
    public byte[] serialize(T t) throws SerializationException {
        return JSON.toJSONBytes(t);
    }

    @Override
    public T deserialize(byte[] bytes) throws SerializationException {
        return JSON.parseObject(bytes,type);
    }
}