package org.moonzhou.redis.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.Duration;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author moonzhou
 */
@Slf4j
@Component
public class RedisUtil {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 工具方法建议使用类名直接调用，不需要实例化调用，这样方法就必须使用static进行修饰，从而容器生成的redisTemplate也需要使用该static修饰的变量来进行转换使用
     */
    private static RedisTemplate<String, Object> s_redisTemplate;

    @PostConstruct
    public void init() {
        s_redisTemplate = redisTemplate;
    }

    /**
     * 指定缓存失效时间
     *
     * @param key  键
     * @param timeout 时间（秒）
     * @return true / false
     */
    public static Boolean expire(final String key, final Duration timeout) {
        Boolean ret = s_redisTemplate.expire(key, timeout.toMillis(), TimeUnit.MILLISECONDS);
        return ret != null && ret;
    }

    /**
     * 根据 key 获取过期时间
     *
     * @param key 键
     * @return
     */
    public static Long getExpire(final String key) {
        return s_redisTemplate.getExpire(key, TimeUnit.SECONDS);
    }

    /**
     * 判断 key 是否存在
     *
     * @param key 键
     * @return true / false
     */
    public static boolean hasKey(final String key) {
        Boolean ret = s_redisTemplate.hasKey(key);
        return ret != null && ret;
    }

    /**
     * 删除缓存
     *
     * @param key 键（一个或者多个）
     * @SuppressWarnings("unchecked") 忽略类型转换警告
     */
    /*@SuppressWarnings("unchecked")
    public static void del(String... key) {
        if (key != null && key.length > 0) {
            if (key.length == 1) {
                s_redisTemplate.delete(key[0]);
            } else {
//                传入一个 Collection<String> 集合
                s_redisTemplate.delete(Arrays.asList(key));
            }
        }
    }*/

    /**
     * 删除单个key
     *
     * @param key 键
     * @return true=删除成功；false=删除失败
     */
    public static Boolean del(final String key) {
        Boolean ret = s_redisTemplate.delete(key);
        return ret != null && ret;
    }

    /**
     * 删除多个key
     *
     * @param keys 键集合
     * @return 成功删除的个数
     */
    public static Long del(final Collection<String> keys) {
        Long ret = s_redisTemplate.delete(keys);
        return ret == null ? 0 : ret;
    }

//    ============================== String ==============================

    /**
     * 普通缓存放入
     *
     * @param key   键
     * @param value 值
     */
    public static void set(final String key, final Object value) {
        s_redisTemplate.opsForValue().set(key, value);
    }

    /**
     * 普通缓存放入并设置时间
     *
     * @param key   键
     * @param value 值
     * @param timeout  时间（秒）
     */
    public static void set(final String key, final Object value, final Duration timeout) {
        s_redisTemplate.opsForValue().set(key, value, timeout.toMillis(), TimeUnit.MILLISECONDS);
    }

    /**
     * 普通缓存获取
     *
     * @param key 键
     * @return 值
     */
    public static Object get(final String key) {
        return s_redisTemplate.opsForValue().get(key);
    }

    /**
     * +1，如果键不存在，则从0开始+1
     * @param key 键
     * @return +1后的值
     */
    public static Long increment(final String key) {
        Long ret = s_redisTemplate.opsForValue().increment(key);
        return ret == null ? (Long) s_redisTemplate.opsForValue().get(key) : ret;
    }

    /**
     * 递增
     *
     * @param key   键
     * @param delta 递增大小：正数往上加，负数往下减
     * @return 增加delta之后的值
     */
    public static Long incr(final String key, long delta) {
        Long ret = s_redisTemplate.opsForValue().increment(key, delta);
        return ret == null ? (Long) s_redisTemplate.opsForValue().get(key) : ret;
    }

//    ============================== Hash ==============================

    /**
     * 往Hash中存入数据
     *
     * @param key   Redis键
     * @param hKey  Hash键
     * @param value 值
     */
    public static void hPut(final String key, final String hKey, final Object value) {
        s_redisTemplate.opsForHash().put(key, hKey, value);
    }

    /**
     * 往Hash中存入多个数据
     *
     * @param key    Redis键
     * @param values Hash键值对
     */
    public static void hPutAll(final String key, final Map<String, Object> values) {
        s_redisTemplate.opsForHash().putAll(key, values);
    }

    /**
     * HashSet 并设置时间
     *
     * @param key  键
     * @param map  值
     * @param timeout 时间
     */
    public static void hPutAll(final String key, final Map<Object, Object> map, final Duration timeout) {
        s_redisTemplate.opsForHash().putAll(key, map);
        if (null != timeout) {
            expire(key, timeout);
        }
    }

    /**
     * 获取Hash中的数据
     *
     * @param key  Redis键
     * @param hKey Hash键
     * @return Hash中的对象
     */
    public static Object hGet(final String key, final String hKey) {
        return s_redisTemplate.opsForHash().get(key, hKey);
    }

    /**
     * 获取多个Hash中的数据
     *
     * @param key   Redis键
     * @param hKeys Hash键集合
     * @return Hash对象集合
     */
    public static List<Object> hMultiGet(final String key, final Collection<Object> hKeys) {
        return s_redisTemplate.opsForHash().multiGet(key, hKeys);
    }

    /**
     * 获取 key 对应的 map
     *
     * @param key 键（no null）
     * @return 对应的多个键值
     */
    public static Map<Object, Object> hGetMap(final String key) {
        return s_redisTemplate.opsForHash().entries(key);
    }

    /**
     * 删除 Hash表 中的值
     *
     * @param key  键
     * @param item 项（可以多个，no null）
     */
    public static Long hDel(final String key, final Object... item) {
        return s_redisTemplate.opsForHash().delete(key, item);
    }

    /**
     * 判断 Hash表 中是否有该键的值
     *
     * @param key  键（no null）
     * @param item 值（no null）
     * @return true / false
     */
    public static Boolean hHasKey(final String key, final String item) {
        return s_redisTemplate.opsForHash().hasKey(key, item);
    }

    /**
     * Hash递增，如果不存在则创建一个，并把新增的值返回
     *
     * @param key  键
     * @param item 项
     * @param delta   递增大小：正数递增，负数递减
     * @return 递增的值
     */
    public static Double hIncr(final String key, final String item, final Double delta) {
        return s_redisTemplate.opsForHash().increment(key, item, delta);
    }

//    ============================== Set ==============================

    /**
     * 将数据放入 set缓存
     *
     * @param key    键值
     * @param values 值（可以多个）
     * @return 成功个数
     */
    public static Long sSet(final String key, final Object... values) {
        Long count = s_redisTemplate.opsForSet().add(key, values);
        return count == null ? 0 : count;
    }

    /**
     * 将数据放入 set缓存，并设置时间
     *
     * @param key    键
     * @param timeout   时间
     * @param values 值（可以多个）
     * @return 成功放入个数
     */
    public static Long sSet(final String key, final Duration timeout, final Object... values) {
        Long count = s_redisTemplate.opsForSet().add(key, values);

        if (null != timeout) {
            expire(key, timeout);
        }

        return count == null ? 0 : count;
    }

    /**
     * 根据 key 获取 set 中的所有值
     *
     * @param key 键
     * @return 值
     */
    public static Set<Object> sGet(final String key) {
        return s_redisTemplate.opsForSet().members(key);
    }

    /**
     * 从键为 key 的 set 中，根据 value 查询是否存在
     *
     * @param key   键
     * @param value 值
     * @return true / false
     */
    public static Boolean sHasKey(final String key, final Object value) {
        Boolean ret = s_redisTemplate.opsForSet().isMember(key, value);
        return null == ret ? Boolean.FALSE : ret;
    }

    /**
     * 获取 set缓存的长度
     *
     * @param key 键
     * @return 长度
     */
    public static Long sGetSetSize(final String key) {
        Long size = s_redisTemplate.opsForSet().size(key);
        return size == null ? 0 : size;
    }

    /**
     * 移除 set缓存中，值为 value 的
     *
     * @param key    键
     * @param values 值
     * @return 成功移除个数
     */
    public static Long setRemove(final String key, final Object... values) {
        Long count = s_redisTemplate.opsForSet().remove(key, values);
        return count == null ? 0 : count;
    }

//    ============================== List ==============================

    /**
     * 将值 value 插入键为 key 的 list 中，如果 list 不存在则创建空 list
     *
     * @param key   键
     * @param value 值
     * @return true / false
     */
    public static Long lRightPush(final String key, final Object value) {
        Long ret = s_redisTemplate.opsForList().rightPush(key, value);
        return null == ret ? 0 : ret;
    }

    /**
     * 将值 value 插入键为 key 的 list 中，并设置时间
     *
     * @param key   键
     * @param value 值
     * @param timeout  时间
     * @return true / false
     */
    public static Long lRightPush(final String key, final Object value, final Duration timeout) {
        Long ret = s_redisTemplate.opsForList().rightPush(key, value);

        if (null != timeout) {
            expire(key, timeout);
        }

        return null == ret ? 0 : ret;
    }

    /**
     * 将 values 插入键为 key 的 list 中
     *
     * @param key    键
     * @param values 值
     * @return 存入的个数
     */
    public static Long lRightPushAll(final String key, final List<Object> values) {
        Long ret = s_redisTemplate.opsForList().rightPushAll(key, values);
        return null == ret ? 0 : ret;
    }

    /**
     * 将 values 插入键为 key 的 list 中，并设置时间
     *
     * @param key    键
     * @param values 值
     * @param timeout   时间
     * @return 存入的个数
     */
    public static Long lRightPushAll(String key, List<Object> values, final Duration timeout) {
        Long ret = s_redisTemplate.opsForList().rightPushAll(key, values);

        if (null != timeout) {
            expire(key, timeout);
        }

        return null == ret ? 0 : ret;
    }

    /**
     * 在集合的指定位置插入元素,如果指定位置已有元素，则覆盖，没有则新增，超过集合下标+n则会报错
     *
     * @param key   键
     * @param index 索引
     * @param value 值
     */
    public static void lSetByIndex(final String key, final long index, final Object value) {
        s_redisTemplate.opsForList().set(key, index, value);
    }

    /**
     * 获取 list缓存的内容
     *
     * @param key   键
     * @param start 开始位置
     * @param end   结束位置（start=0，end=-1表示获取全部元素）
     * @return List对象
     */
    public static List<Object> lGet(final String key, final long start, final long end) {
        return s_redisTemplate.opsForList().range(key, start, end);
    }

    /**
     * 获取 list缓存的长度
     *
     * @param key 键
     * @return 长度
     */
    public static Long lSize(final String key) {
        Long size = s_redisTemplate.opsForList().size(key);
        return null == size ? 0 : size;
    }

    /**
     * 根据索引 index 获取键为 key 的 list 中的元素
     *
     * @param key   键
     * @param index 索引
     *              当 index >= 0 时 {0:表头, 1:第二个元素}
     *              当 index < 0 时 {-1:表尾, -2:倒数第二个元素}
     * @return 值
     */
    public static Object lGetByIndex(final String key, final long index) {
        return s_redisTemplate.opsForList().index(key, index);
    }

    /**
     * 在键为 key 的 list 中删除值为 value 的元素
     *
     * @param key   键
     * @param count 如果 count == 0 则删除 list 中所有值为 value 的元素
     *              如果 count > 0 则删除 list 中最左边那个值为 value 的元素
     *              如果 count < 0 则删除 list 中最右边那个值为 value 的元素
     * @param value 值
     * @return 删除元素的个数
     */
    public static Long lRemove(final String key, final long count, final Object value) {
        Long ret = s_redisTemplate.opsForList().remove(key, count, value);
        return null == ret ? 0 : ret;
    }

}