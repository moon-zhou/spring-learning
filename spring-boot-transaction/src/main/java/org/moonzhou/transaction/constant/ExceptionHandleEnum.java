package org.moonzhou.transaction.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

/**
 * @author moon zhou
 * @version 1.0
 * @description 状态枚举
 * @date 2022/10/26 11:26
 */
@Getter
@AllArgsConstructor
public enum ExceptionHandleEnum {
    LOG(0,"仅仅输出日志"),
    BIZ_NO_EXCEPTION(1,"业务处理，不抛异常"),
    THROW_RUNTIME_EXCEPTION(2,"抛出RuntimeException"),
    THROW_CUSTOM_RUNTIME_EXCEPTION(3,"抛出自定义的RuntimeException"),
    THROW_THE_SAME_EXCEPTION(4,"抛出之前相同的Exception"),

    ;

    private int value;
    private String desc;

    public static ExceptionHandleEnum getHandler(int value) {
        ExceptionHandleEnum conditionEnum = Arrays.asList(ExceptionHandleEnum.values()).stream()
                .filter(exceptionHandle -> value == exceptionHandle.getValue()).findFirst().orElse(null);

        return conditionEnum;
    }

    public static boolean isRuntimeException(int value) {
        return ExceptionHandleEnum.THROW_RUNTIME_EXCEPTION.value == value
                || ExceptionHandleEnum.THROW_CUSTOM_RUNTIME_EXCEPTION.value == value;
    }

    public static boolean isRuntimeException(ExceptionHandleEnum exceptionHandleEnum) {
        return ExceptionHandleEnum.THROW_RUNTIME_EXCEPTION == exceptionHandleEnum
                || ExceptionHandleEnum.THROW_CUSTOM_RUNTIME_EXCEPTION == exceptionHandleEnum;
    }

    public static boolean isNoException(int value) {
        return ExceptionHandleEnum.LOG.value == value
                || ExceptionHandleEnum.BIZ_NO_EXCEPTION.value == value;
    }

    public static boolean isNoException(ExceptionHandleEnum exceptionHandleEnum) {
        return ExceptionHandleEnum.LOG == exceptionHandleEnum
                || ExceptionHandleEnum.BIZ_NO_EXCEPTION == exceptionHandleEnum;
    }
}
