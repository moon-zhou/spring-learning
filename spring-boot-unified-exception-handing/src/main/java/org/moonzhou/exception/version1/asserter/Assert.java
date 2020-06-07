package org.moonzhou.exception.version1.asserter;

import org.apache.commons.lang3.StringUtils;
import org.moonzhou.exception.version1.customedexception.BaseException;

/**
 * @author moon-zhou <ayimin1989@163.com>
 * @version V1.0.0
 * @description
 * @date 2020/6/6 21:50
 * @since 1.0
 */
public interface Assert {
    /**
     * 创建异常
     * @param args
     * @return
     */
    BaseException newException(Object... args);

    /**
     * 创建异常
     * @param t
     * @param args
     * @return
     */
    BaseException newException(Throwable t, Object... args);

    /**
     * <p>断言对象<code>obj</code>非空。如果对象<code>obj</code>为空，则抛出异常
     *
     * @param obj 待判断对象
     */
    default void assertNotNull(Object obj) {
        if (obj == null) {
            // 此处为模板方法，调用的是子类自己的实现
            throw newException(obj);
        }
    }

    /**
     * 断言字符串必须有显示的值
     * @param obj
     */
    default void assertNotBlank(String obj) {
        if (StringUtils.isBlank(obj)) {
            throw newException(obj);
        }
    }

    /**
     * <p>断言对象<code>obj</code>非空。如果对象<code>obj</code>为空，则抛出异常
     * <p>异常信息<code>message</code>支持传递参数方式，避免在判断之前进行字符串拼接操作
     *
     * @param obj 待判断对象
     * @param args message占位符对应的参数列表
     */
    default void assertNotNull(Object obj, Object... args) {
        if (obj == null) {
            // 此处为模板方法，调用的是子类自己的实现
            throw newException(args);
        }
    }
}
