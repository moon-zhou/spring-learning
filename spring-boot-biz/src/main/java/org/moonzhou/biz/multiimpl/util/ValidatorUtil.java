package org.moonzhou.biz.multiimpl.util;

import javax.validation.*;
import java.util.Set;

/**
 * @author moon zhou
 * @version 1.0
 * @description:
 */
public class ValidatorUtil {
    private static Validator validator;

    static {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    /**
     * 校验对象内所有的字段
     *
     * @param validateBean 被检查的对象
     * @param <T>          对象类型
     * @return 成功返回固定信息：VALIDATE_PASS，失败默认返回第一个异常信息
     */
    public static <T> void validate(T validateBean) {
        Set<ConstraintViolation<T>> validateResult = validator.validate(validateBean);
        if (!validateResult.isEmpty()) {
            throw new ConstraintViolationException(validateResult);
        }
    }

    /**
     * 校验对象内的指定字段
     *
     * @param validateBean 被检查的对象
     * @param propertyName 指定字段名称
     * @param <T>          对象类型
     * @return 成功返回固定信息：VALIDATE_PASS，失败默认返回第一个异常信息
     */
    public static <T> void validateProperty(T validateBean, String propertyName) {
        Set<ConstraintViolation<T>> validateResult = validator.validateProperty(validateBean, propertyName);
        if (!validateResult.isEmpty()) {
            throw new ConstraintViolationException(validateResult);
        }
    }
}
