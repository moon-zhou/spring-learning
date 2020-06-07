package org.moonzhou.exception.version1.customedexception;

import cn.hutool.core.util.ArrayUtil;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.moonzhou.exception.version1.asserter.IResponseEnum;

/**
 * @author moon-zhou <ayimin1989@163.com>
 * @version V1.0.0
 * @description
 * @date 2020/6/6 21:51
 * @since 1.0
 */
@Slf4j
@Getter
public class BaseException extends RuntimeException {

    protected IResponseEnum responseEnum;

    protected Object[] args;

    public BaseException(IResponseEnum responseEnum, Object[] args, String message) {
        super(responseEnum.getCode() + "|" + responseEnum.getMessage() + ArrayUtil.join(args, "-") + message);
    }

    public BaseException(IResponseEnum responseEnum, Object[] args, String message, Throwable cause) {
        super(responseEnum.getCode() + "|" + responseEnum.getMessage() + ArrayUtil.join(args, "-") + message,
                cause);
    }
}
