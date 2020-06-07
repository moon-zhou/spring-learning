package org.moonzhou.configure;

import lombok.extern.slf4j.Slf4j;
import org.moonzhou.i18n.I18nService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;

/**
 * @author moon-zhou <ayimin1989@163.com>
 * @version V1.0.0
 * @description
 * @date 2020/6/6 20:45
 * @since 1.0
 */
@Slf4j
@Configuration
public class WebMvcConfiguration {
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
}
