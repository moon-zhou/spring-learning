package org.moonzhou.biz.constant;

import lombok.Getter;

/**
 * 登录方式枚举
 * @author moon zhou
 */
@Getter
public enum LoginTypeEnum {
    /**
     * 账密登录
     */
    USERNAME_PASSWORD("username-password", "账密登录"),

    /**
     * 短信登录
     */
    SMS("sms", "短信登录"),

    ;

    LoginTypeEnum(String value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    private final String value;
    private final String desc;
}
