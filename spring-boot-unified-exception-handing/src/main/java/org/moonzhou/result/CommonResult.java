package org.moonzhou.result;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.moonzhou.constant.ErrorCodeEnum;

/**
 * 通用返回结果，默认成功
 * @author moon-zhou <ayimin1989@163.com>
 * @version V1.0.0
 * @description
 * @date 2020/6/7 17:53
 * @since 1.0
 */
public class CommonResult extends BaseResult {

    public CommonResult(String responseCode, String responseMessage, Object data) {
        super(responseCode, responseMessage, data);
        initSuccess();
    }

    public CommonResult(ErrorCodeEnum errorCodeEnum, Object data) {
        super(errorCodeEnum, data);
        initSuccess();
    }

    public CommonResult(ErrorCodeEnum errorCodeEnum, String responseMessage, Object data) {
        super(errorCodeEnum, responseMessage, data);
        initSuccess();
    }

    public CommonResult(int code, String message) {
        super(code, message);
        initSuccess();
    }

    /**
     * 默认成功"0000"
     */
    private void initSuccess() {
        super.setResponseCode(ErrorCodeEnum.SUCCESS.getResponseCode());
    }

    public boolean isSuccess() {
        return ErrorCodeEnum.SUCCESS.getResponseCode().equals(super.getResponseCode());
    }

    @Override
    public String toString() {

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
