package org.moonzhou.exception.version2;

import cn.hutool.json.JSON;
import lombok.extern.slf4j.Slf4j;
import org.moonzhou.result.CommonResult;

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
     * 是否成功
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
