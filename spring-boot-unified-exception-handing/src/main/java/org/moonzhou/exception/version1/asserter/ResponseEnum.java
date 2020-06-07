package org.moonzhou.exception.version1.asserter;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 返回值枚举-断言
 *
 * 此处枚举功能不单一，不仅仅是枚举，还是断言，耦合性太强，其实后续可以单独拆分，断言本身及消息，枚举更多的是断言里的消息，此处有点本末倒置
 * @author moon-zhou
 */
@Getter
@AllArgsConstructor
public enum ResponseEnum implements BusinessExceptionAssert {

    /**
     * Bad licence type
     */
    BAD_LICENCE_TYPE(7001, "Bad licence type."),

    /**
     * Licence not found
     */
    LICENCE_NOT_FOUND(7002, "Licence not found."),

    /**
     * User not found
     */
    USER_NO_EXIST(7401, "no user."),

    /**
     * Param is null or empty
     */
    PARAM_NOT_NULL(9998, "param null or empty"),

    ;

    /**
     * 返回码
     */
    private int code;
    /**
     * 返回消息
     */
    private String message;
}