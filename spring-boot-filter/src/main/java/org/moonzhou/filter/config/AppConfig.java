/*
 * Copyright (C), 2002-2021, moon-zhou
 * FileName: AppConfig.java
 * Author:   moon-zhou
 * Email:    ayimin1989@163.com
 * Date:     2021/7/5 17:10
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名    修改时间    版本号       描述
 */
package org.moonzhou.filter.config;

import org.moonzhou.filter.constant.FilterConstants;
import org.moonzhou.filter.registration.RegistrationFilter01;
import org.moonzhou.filter.registration.RegistrationFilter02;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashSet;
import java.util.Set;

/**
 * 功能描述:<br>
 *
 * @author moon-zhou
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@Configuration
public class AppConfig {

    @Bean
    public FilterRegistrationBean<RegistrationFilter01> registrationFilter01() {

        FilterRegistrationBean<RegistrationFilter01> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(new RegistrationFilter01());

        Set<String> urlPatterns = new HashSet<>();
        urlPatterns.add("/filter/registrationfilter");
        filterRegistrationBean.setUrlPatterns(urlPatterns);

        filterRegistrationBean.setOrder(FilterConstants.ORDERED_HIGHEST_LEVEL + 3);

        return filterRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean<RegistrationFilter02> registrationFilter02() {

        FilterRegistrationBean<RegistrationFilter02> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(new RegistrationFilter02());

        Set<String> urlPatterns = new HashSet<>();
        urlPatterns.add("/filter/registrationfilter");
        filterRegistrationBean.setUrlPatterns(urlPatterns);

        filterRegistrationBean.setOrder(FilterConstants.ORDERED_HIGHEST_LEVEL + 4);

        return filterRegistrationBean;
    }
}
