package org.moonzhou.exception;

/**
 * 自定义运行时异常：业务异常
 * @author moonzhou
 */
public class FileTypeValidationException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public FileTypeValidationException() {
        super();
    }

    public FileTypeValidationException(String message) {
        super(message);
    }

    public FileTypeValidationException(Throwable cause) {
        super(cause);
    }

    public FileTypeValidationException(String message, Throwable cause) {
        super(message, cause);
    }
}