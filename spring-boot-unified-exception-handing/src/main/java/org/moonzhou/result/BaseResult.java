package org.moonzhou.result;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.moonzhou.constant.ErrorCodeEnum;

/**
 * @author moon-zhou <ayimin1989@163.com>
 * @version V1.0.0
 * @description
 * @date 2020/6/6 21:09
 * @since 1.0
 */
@Getter
@Setter
@ToString
public class BaseResult<T> {

    private String responseCode;
    private String responseMessage;
    private T data;

    public BaseResult(String responseCode, String responseMessage, T data) {
        this.responseCode = responseCode;
        this.responseMessage = responseMessage;
        this.data = data;
    }

    public BaseResult(ErrorCodeEnum errorCodeEnum, T data) {
        this.responseCode = errorCodeEnum.getResponseCode();
        this.responseMessage = errorCodeEnum.getResponseMessage();
        this.data = data;
    }

    public BaseResult(ErrorCodeEnum errorCodeEnum, String responseMessage, T data) {
        this.responseCode = errorCodeEnum.getResponseCode();
        this.responseMessage = responseMessage;
        this.data = data;
    }

    public BaseResult(int code, String message) {
        this.responseCode = String.valueOf(code);
        this.responseMessage = message;
    }

    public BaseResult(String responseCode, String responseMessage) {
        this.responseCode = responseCode;
        this.responseMessage = responseMessage;
    }
}
