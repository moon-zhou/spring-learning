package org.moonzhou.i18n;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;

/**
 * 使用注解方式来包装MessageSource
 * @author moon-zhou <ayimin1989@163.com>
 * @version V1.0.0
 * @description
 * @date 2020/6/7 14:48
 * @since 1.0
 */
@Service
public class I18nAnnotationService {

    @Autowired
    private MessageSource messageSource;

    public String getMessageDefault(String msgKey, Object[] args) {
        return messageSource.getMessage(msgKey, args, Locale.getDefault());
    }

    public String getMessageDefault(String msgKey) {
        return messageSource.getMessage(msgKey, null, Locale.getDefault());
    }

    public String getMessage(String msgKey, Locale locale) {
        return messageSource.getMessage(msgKey, null, locale);
    }

    public String getMessage(String msgKey, Object[] args, Locale locale) {
        return messageSource.getMessage(msgKey, args, locale);
    }
}
