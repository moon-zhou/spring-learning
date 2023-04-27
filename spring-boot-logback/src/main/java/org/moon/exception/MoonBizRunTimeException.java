package org.moon.exception;

/**
 * @author moon zhou
 * @version 1.0
 * @description:
 * @date 2023/4/26 17:10
 */
public class MoonBizRunTimeException extends RuntimeException {
    public MoonBizRunTimeException() {
    }

    public MoonBizRunTimeException(String message) {
        super(message);
    }

    public MoonBizRunTimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public MoonBizRunTimeException(Throwable cause) {
        super(cause);
    }

    public MoonBizRunTimeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
