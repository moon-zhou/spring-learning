package org.moonzhou.transaction.exception;

/**
 * @author moon zhou
 * @version 1.0
 * @description: 业务异常，用于@Transactional异常属性配置
 * @date 2022/10/21 17:44
 */
public class AccountBiz1RuntimeException extends RuntimeException {
    public AccountBiz1RuntimeException() {
    }

    public AccountBiz1RuntimeException(String message) {
        super(message);
    }

    public AccountBiz1RuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public AccountBiz1RuntimeException(Throwable cause) {
        super(cause);
    }
}
