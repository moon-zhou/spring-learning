package org.moonzhou.dto;

public class Result<T> {
    private int code;
    private String message;
    private T data;

    private Result(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static Result<Void> success() {
        return new Result(200, (String)null, (Object)null);
    }

    public static <T> Result<T> success(T data) {
        return new Result(200, (String)null, data);
    }

    public static Result<Void> failed(int code, String message) {
        return new Result(code, message, (Object)null);
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

    public Result() {
    }
}