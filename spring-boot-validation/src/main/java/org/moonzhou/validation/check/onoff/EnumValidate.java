package org.moonzhou.validation.check.onoff;

/**
 * 用于实现枚举的校验
 *
 * @param <T>
 * @author moon zhou
 */
public interface EnumValidate<T> {

    /**
     * 校验枚举值是否存在
     */
    boolean existValidate(T value);
}
