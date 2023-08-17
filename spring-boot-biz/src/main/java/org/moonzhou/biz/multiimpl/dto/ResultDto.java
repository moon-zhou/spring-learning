package org.moonzhou.biz.multiimpl.dto;

public class ResultDto<T> {
    private int code;
    private String message;
    private T data;

    private ResultDto(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static ResultDto<Void> success() {
        return new ResultDto(200, (String)null, (Object)null);
    }

    public static <T> ResultDto<T> success(T data) {
        return new ResultDto(200, (String)null, data);
    }

    public static ResultDto<Void> failed(int code, String message) {
        return new ResultDto(code, message, (Object)null);
    }

    public int getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }

    public T getData() {
        return this.data;
    }

    public ResultDto() {
    }
}
