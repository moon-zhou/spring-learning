package org.moonzhou.springsession.config;

import com.alibaba.fastjson2.support.spring.data.redis.GenericFastJsonRedisSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.serializer.RedisSerializer;

/**
 * Spring-Session 默认使用 JDK 序列化机制，要求类实现 Serializable 接口，序列化后是二进制字节数组，不易看懂。使用 JSON 序列化机制，序列化后的字符串容易看懂。
 * @author moon zhou
 */
@Configuration
public class SessionConfig {
    @Bean
    public RedisSerializer<Object> springSessionDefaultRedisSerializer() {
        return new GenericFastJsonRedisSerializer();
    }
}