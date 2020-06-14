package org.moonzhou.exception.version3;

/**
 * 自定义运行时异常
 *
 * @author moon-zhou
 */
public class AppException extends RuntimeException {

    private static final long serialVersionUID = -10080041155663773L;

    private String code;

    /**
     * 报文信息
     */
    private String message;

    public AppException() {
    }

    public AppException(String message) {
        super(message);
        this.message = message;
    }

    public AppException(String message, Throwable e) {
        super(message, e);
        this.message = message;
    }

    public AppException(String code, String message, Throwable e) {
        super(message, e);
        this.code = code;
        this.message = message;
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}