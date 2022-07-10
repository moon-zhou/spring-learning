package org.moonzhou.redis;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.moonzhou.redis.util.RedisUtil;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Duration;

/**
 * @author moonzhou
 */
@SpringBootTest
public class TestRedisUtil {

    @Test
    public void test() {
        String key = "redisUtil";
        String value = "test";


        RedisUtil.set(key, value, Duration.ofSeconds(30));

        Long expire = RedisUtil.getExpire(key);
        Assertions.assertNotNull(expire);

        String outValue = (String)RedisUtil.get(key);
        Assertions.assertEquals(outValue, value);

        Boolean del = RedisUtil.del(key);
        Assertions.assertTrue(del);
    }

    @Test
    public void testIncr() {
        String key = "testIncr";

        Long incr = RedisUtil.increment(key);
        Assertions.assertEquals(1L, incr);

        incr = RedisUtil.increment(key);
        Assertions.assertEquals(2L, incr);

        incr = RedisUtil.incr(key, 3L);
        Assertions.assertEquals(5L, incr);

        incr = RedisUtil.incr(key, -2L);
        Assertions.assertEquals(3L, incr);

        Boolean del = RedisUtil.del(key);
        Assertions.assertTrue(del);
    }
}
