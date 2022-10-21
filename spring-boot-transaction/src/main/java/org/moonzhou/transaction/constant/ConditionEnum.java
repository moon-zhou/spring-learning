package org.moonzhou.transaction.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

/**
 * @author moon zhou
 * @version 1.0
 * @description 状态枚举
 * @date 2022/10/21 13:43
 */
@Getter
@AllArgsConstructor
public enum ConditionEnum {
    NORMAL(0,"正常情况"),
    RUNTIME_EXCEPTION(1,"运行时异常"),

    ;

    private int condition;
    private String desc;

    public static ConditionEnum getCondition(int value) {
        ConditionEnum conditionEnum = Arrays.asList(ConditionEnum.values()).stream()
                .filter(condition -> value == condition.getCondition()).findFirst().orElse(null);

        return conditionEnum;
    }

    public static boolean isRuntimeException(int condition) {
        return ConditionEnum.RUNTIME_EXCEPTION.condition == condition;
    }

    public static boolean isRuntimeException(ConditionEnum condition) {
        return ConditionEnum.RUNTIME_EXCEPTION == condition;
    }
}
