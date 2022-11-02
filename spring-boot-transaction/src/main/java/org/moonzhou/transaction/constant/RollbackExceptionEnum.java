package org.moonzhou.transaction.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

/**
 * @author moon zhou
 * @version 1.0
 * @description rollback 异常方式枚举
 * @date 2022/10/26 17:39
 */
@Getter
@AllArgsConstructor
public enum RollbackExceptionEnum {
    EXCEPTION(0,"Exception"),
    RUNTIME_EXCEPTION(1,"RuntimeException"),
    ACCOUNT_BIZ_ONE_EXCEPTION(2,"AccountBiz1RuntimeException"),
    ACCOUNT_BIZ_TWO_EXCEPTION(3,"AccountBiz2RuntimeException"),

    ;

    private int value;
    private String desc;

    public static RollbackExceptionEnum getRollbackException(int value) {
        RollbackExceptionEnum conditionEnum = Arrays.asList(RollbackExceptionEnum.values()).stream()
                .filter(rollbackException -> value == rollbackException.getValue()).findFirst().orElse(null);

        return conditionEnum;
    }

}
