package org.moonzhou.i18n;

import org.springframework.context.MessageSource;

import java.util.Locale;

/**
 * 在WebMvcConfiguration里初始化的bean
 * @author moon-zhou <ayimin1989@163.com>
 * @version V1.0.0
 * @description
 * @date 2020/6/6 23:17
 * @since 1.0
 */
public class I18nService {

    private final MessageSource messageSource;

    public I18nService(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public String getMessageDefault(String msgKey, Object[] args) {
        return messageSource.getMessage(msgKey, args, Locale.getDefault());
    }

    public String getMessageDefault(String msgKey) {
        return messageSource.getMessage(msgKey, null, Locale.getDefault());
    }

    public String getMessage(String msgKey, Object[] args, Locale locale) {
        return messageSource.getMessage(msgKey, args, locale);
    }

    public String getMessage(String msgKey, Locale locale) {
        return messageSource.getMessage(msgKey, null, locale);
    }
}
