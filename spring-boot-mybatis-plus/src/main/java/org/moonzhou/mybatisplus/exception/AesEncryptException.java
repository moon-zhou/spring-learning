package org.moonzhou.mybatisplus.exception;

/**
 * @author moon zhou
 */
public class AesEncryptException extends RuntimeException {

    public AesEncryptException(String message) {
        super(message);
    }

    public AesEncryptException(Throwable cause) {
        super(cause);
    }

    public AesEncryptException(String message, Throwable cause) {
        super(message, cause);
    }
}