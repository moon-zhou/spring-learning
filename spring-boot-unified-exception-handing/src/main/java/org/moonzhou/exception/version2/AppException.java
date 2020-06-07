package org.moonzhou.exception.version2;

/**
 * 自定义运行时异常
 *
 * @author moon-zhou
 */
public class AppException extends RuntimeException {

    private static final long serialVersionUID = -10080041155663773L;

    /**
     * 报文信息
     */
    private String description;

    public AppException() {
    }

    public AppException(String description) {
        super(description);
        this.description = description;
    }

    public AppException(String description, Throwable e) {
        super(description, e);
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

}