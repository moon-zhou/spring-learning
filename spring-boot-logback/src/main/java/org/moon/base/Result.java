package org.moon.base;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
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
        return new Result<>(200, null, null);
    }

    public static <T> Result<T> success(T data) {
        return new Result<>(200, null, data);
    }

    public static Result<Void> failed(int code, String message) {
        return new Result<>(code, message, null);
    }

    public static <T> Result<T> failed(int code, String message, T data) {
        return new Result<>(code, message, data);
    }
}