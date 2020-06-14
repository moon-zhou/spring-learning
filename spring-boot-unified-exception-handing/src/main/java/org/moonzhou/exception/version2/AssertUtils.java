package org.moonzhou.exception.version2;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.moonzhou.constant.ErrorCodeEnum;
import org.moonzhou.result.CommonResult;
import org.springframework.util.Assert;

import java.util.Map;

/**
 * 断言工具类，用于基本的判断，依赖spring的org.springframework.util.Assert
 *
 * @author moon-zhou <ayimin1989@163.com>
 * @version V1.0.0
 * @description
 * @date 2020/6/7 17:45
 * @since 1.0
 */
@Slf4j
public class AssertUtils {
    private AssertUtils() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * 断言为真
     *
     * @param expression
     * @param description
     */
    public static void isTrue(Boolean expression, String description) {
        try {
            Assert.isTrue(expression, description);
        } catch (Exception e) {
            dealException(description, e);
        }
    }
    public static void isTrue(Boolean expression, ErrorCodeEnum errorCodeEnum) {
        try {
            Assert.isTrue(expression, errorCodeEnum.getResponseMessage());
        } catch (Exception e) {
            dealException(errorCodeEnum.getResponseMessage(), e);
        }
    }

    /**
     * 字符串断言不为空
     *
     * @param str
     * @param description
     */
    public static void hasLength(String str, String description) {
        try {
            Assert.hasLength(str, description);
        } catch (Exception e) {
            dealException(description, e);
        }
    }
    public static void hasLength(String str, ErrorCodeEnum errorCodeEnum) {
        try {
            Assert.hasLength(str, errorCodeEnum.getResponseMessage());
        } catch (Exception e) {
            dealException(errorCodeEnum.getResponseMessage(), e);
        }
    }

    /**
     * 对象断言不为null
     * @param obj
     * @param description
     */
    public static void isNotNull(Object obj, String description) {
        try {
            Assert.notNull(obj, description);
        } catch (Exception ex) {
            dealException(description, ex);
        }
    }
    public static void isNotNull(Object obj, ErrorCodeEnum errorCodeEnum) {
        try {
            Assert.notNull(obj, errorCodeEnum.getResponseMessage());
        } catch (Exception ex) {
            dealException(errorCodeEnum.getResponseMessage(), ex);
        }
    }

    /**
     * 通用map结果是否成功
     * 往往有些接口设计按照map格式，不按照统一抽象结果对象来
     * @param result
     * @param description
     */
    public static void isSuccess(Map<String, Object> result, String description) {
        if (!MapResultHandle.isSuccess(result)) {
            String content = description + " ssf return result is:" + JSON.toJSONString(result);
            log.error(content);
            throw new AppException(content);
        }
    }

    /**
     * 标准返回结果是否成功
     *
     * @param result
     * @param description
     */
    public static void isSuccess(CommonResult result, String description) {
        if (!result.isSuccess()) {
            String content = description + " return result is:" + result;
            log.error(content);

            throw new AppException(content);
        }
    }

    /**
     * 异常处理
     *
     * @param description 描述
     * @param e 异常
     */
    private static void dealException(String description, Exception e) {
        log.error(description);
        log.error("error:{}", e);
        throw new AppException(description);
    }

}
