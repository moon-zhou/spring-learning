package org.moonzhou.biz.noif.constant;

import lombok.Getter;

/**
 * 支付方式枚举
 *
 * @author moon zhou
 */
@Getter
public enum PayEnum {

    /**
     * 支付宝
     */
    ALI_PAY("ali", "支付宝支付"),

    /**
     * 微信
     */
    WE_CHAT_PAY("wechat", "微信支付"),

    /**
     * 京东
     */
    JINGDONG_PAY("jingdong", "京东支付"),

    /**
     * 苏宁
     */
    SUNING_PAY("suning", "苏宁支付")
    ;

    PayEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    private final String code;
    private final String desc;

}
