package org.moonzhou.configure;

import lombok.extern.slf4j.Slf4j;
import org.moonzhou.i18n.I18nService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;

import java.util.Locale;

/**
 * 通过注解方式将I18封装的方法加入spring上下文进行管理
 */
@Slf4j
@Configuration
public class I18nConfiguration {

    @Bean
    public I18nService i18nService() {
        log.info("-----------------init i18nService bean");
        return new I18nService(getMessageSource());
    }

    /**
     * bean不显示注入名称，直接将方法名设置为bean名称也可：messageSource
     * @return
     */
    @Bean(name = "messageSource")
    public ResourceBundleMessageSource getMessageSource() {
        log.info("-----------------init messageSource bean");

        //Locale.setDefault(Locale.ENGLISH);
        ResourceBundleMessageSource source = new ResourceBundleMessageSource();

        // name of the resource bundle
        source.setBasenames("i18n/messages");
        source.setUseCodeAsDefaultMessage(true);
        source.setDefaultEncoding("UTF-8");

        return source;
    }

    @Bean
    public LocaleResolver localeResolver() {
        /**
         * LocaleResolver用于存储语言，这里将语言的信息保存在Cookie中，所以使用现成的实现CookieLocaleResolver。
         * 如果需要将语言保存在Session中，可以使用SessionLocaleResolver。
         *
         * SessionLocaleResolver slr = new SessionLocaleResolver();
         */
        CookieLocaleResolver localeResolver = new CookieLocaleResolver();
        localeResolver.setDefaultLocale(Locale.CHINA);
        return localeResolver;
    }
}