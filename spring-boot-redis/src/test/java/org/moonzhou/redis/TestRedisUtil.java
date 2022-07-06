package org.moonzhou.redis;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.moonzhou.redis.util.RedisUtil;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author moonzhou
 */
@SpringBootTest
public class TestRedisUtil {

    @Test
    public void test() {
        String key = "redisUtil";
        String value = "test";

        boolean setResult = RedisUtil.set(key, value, 30L);
        Assertions.assertTrue(setResult);

        Long expire = RedisUtil.getExpire(key);
        Assertions.assertNotNull(expire);

        String outValue = (String)RedisUtil.get(key);
        Assertions.assertEquals(outValue, value);

        RedisUtil.del(key);
    }
}
