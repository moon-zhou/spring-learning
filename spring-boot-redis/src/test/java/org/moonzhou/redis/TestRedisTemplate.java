package org.moonzhou.redis;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
public class TestRedisTemplate {
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @Test
    public void testIncr() {
        Long result = redisTemplate.opsForValue().increment("testIncr");
        Assertions.assertEquals(result, 1L);

        Boolean delResult = redisTemplate.delete("testIncr");
        Assertions.assertTrue(delResult);
    }



}
