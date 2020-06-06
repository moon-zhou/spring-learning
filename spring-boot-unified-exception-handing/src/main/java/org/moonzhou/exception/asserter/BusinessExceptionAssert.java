package org.moonzhou.exception.asserter;

import org.moonzhou.exception.customedexception.BaseException;
import org.moonzhou.exception.customedexception.BusinessException;

import java.text.MessageFormat;

public interface BusinessExceptionAssert extends IResponseEnum, Assert {

    @Override
    default BaseException newException(Object... args) {
        // getMessage通过接口来获取枚举里的值，但其实枚举里本身可以直接获取，此处没看到抽取接口的必要性
        String msg = MessageFormat.format(this.getMessage(), args);

        return new BusinessException(this, args, msg);
    }

    @Override
    default BaseException newException(Throwable t, Object... args) {
        String msg = MessageFormat.format(this.getMessage(), args);

        return new BusinessException(this, args, msg, t);
    }

}