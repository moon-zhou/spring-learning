package org.moonzhou.redis.service;

import org.apache.commons.lang3.StringUtils;

import org.moonzhou.redis.util.RedisUtil;

import java.time.Duration;
import java.time.LocalDate;

/**
 * 封装分布式自增流水工具类
 *
 * @author moon zhou
 */
public class SerialNum {

    /**
     * 自增流水号在redis中的前缀
     */
    private static final String MOON_AUTO_INCR_SERIAL_NUM_REDIS_KEY_PREFIX = "MOON:PROCESS:ID:";

    /**
     * 自增流水号默认长度
     */
    private static final int AUTO_INCR_SERIAL_NUM_DEFAULT_LENGTH = 3;

    /**
     * 补位字符
     */
    private static final String PAD_STR = "0";

    /**
     * 获取流水号，默认固定3位，不足三位补0
     * 001/002/010/100/999
     * @return 三位流水号
     */
    public static String getFormatSerialNum() {
        return getFormatSerialNum(AUTO_INCR_SERIAL_NUM_DEFAULT_LENGTH);
    }

    /**
     * 获取流水号，返回固定位数，不足位数补0
     * @param length 流水号长度
     * @return 根据传入的位数，生成对应位数的流水号
     */
    public static String getFormatSerialNum(int length) {
        return StringUtils.leftPad(String.valueOf(getSerialNum()), length, PAD_STR);
    }

    public static Long getSerialNum() {
        String key = MOON_AUTO_INCR_SERIAL_NUM_REDIS_KEY_PREFIX + LocalDate.now();
        Long incr = RedisUtil.increment(key);
        if (1 == incr) {
            // 如果为1，表示是当天第一次生成，设置该过期时间为1天，因为incr本身是线程安全的，所以基于该返回值做的判断，也是线程安全的，不会造成多次更新过期时间
            // 潜在问题为如果在进入判断之前，停服了（正常 or 异常），则会导致当次的key会成为一个永久key，所以服务发布时需要避开操作时间段（内部服务并发低），同时需配合redis监控使用（监控永久key）
            // 当天的key，到下一天是不能用的，但是可能没有及时过期，对于本身有实现方案洁癖的，可以考虑只使用一个key
            RedisUtil.expire(key, Duration.ofDays(1L));
        }
        return incr;
    }
}
