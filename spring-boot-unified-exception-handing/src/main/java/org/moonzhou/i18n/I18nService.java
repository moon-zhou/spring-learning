package org.moonzhou.i18n;

import org.springframework.context.MessageSource;

import java.util.Locale;

/**
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

    public String getMessage(String msgKey, Object[] args) {
        return messageSource.getMessage(msgKey, args, Locale.getDefault());
    }

    public String getMessage(String msgKey) {
        return messageSource.getMessage(msgKey, null, Locale.getDefault());
    }
}
