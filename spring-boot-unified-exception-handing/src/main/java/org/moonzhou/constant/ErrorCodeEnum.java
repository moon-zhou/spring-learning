package org.moonzhou.constant;

/**
 * @author moon-zhou <ayimin1989@163.com>
 * @version V1.0.0
 * @description
 * @date 2020/6/6 21:16
 * @since 1.0
 */
public enum ErrorCodeEnum {
    SUCCESS("0000", "SUCCESS", "common success"),
    SYSTEM_ERROR("9999", "system error", "系统异常"),

    PARAM_ERROR("9998", "param error", "参数不合法"),

    USER_ID_ILLEGAL("7400", "user id illegal", "用户id非法"),

    ;
    private String responseCode;

    private String responseMessage;

    private String responseDesc;

    ErrorCodeEnum(String responseCode, String responseMessage, String responseDesc) {
        this.responseCode = responseCode;
        this.responseMessage = responseMessage;
        this.responseDesc = responseDesc;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public String getResponseDesc() {
        return responseDesc;
    }
}
