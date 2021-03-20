/*
 * Copyright (C), 2002-2021, moon-zhou
 * FileName: AppInterceptorConfig.java
 * Author:   moon-zhou
 * Email:    ayimin1989@163.com
 * Date:     2021/3/20 9:14
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名    修改时间    版本号       描述
 */
package org.moonzhou.xss.adapter;

import org.moonzhou.xss.handler.CookieProducerInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * 功能描述: 配置初始化类<br>
 * org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter已过时，
 * 使用org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport
 *
 * @author moon-zhou
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@Component
public class AppInterceptorConfig extends WebMvcConfigurationSupport {

    /**
     * 生产cookie拦截器
     */
    @Autowired
    private CookieProducerInterceptor cookieProducerInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // cookie拦截器初始化，并且设置只拦截controller请求，不拦截静态资源
        registry.addInterceptor(cookieProducerInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/", "/css/**", "/js/**", "/img/**");  // 不能配置.excludePathPatterns("/","/static/**");
    }

    /**
     * WebMvcConfigurationSupport 会导致静态资源无法加载，而WebMvcConfigurerAdapter不会，但是2.0之后adapter已经废弃
     * https://blog.csdn.net/fmwind/article/details/82832758
     *
     * @param registry
     */
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
        super.addResourceHandlers(registry);
    }

    /**
     * 添加跨域配置，此处针对的是监听服务，因为监听的是被攻击者的服务页面上发起的上报数据请求
     *
     * @param registry
     */
    @Override
    protected void addCorsMappings(CorsRegistry registry) {
        super.addCorsMappings(registry);

        registry.addMapping("/listener/**")
                .allowedHeaders("*")
                .allowedMethods("POST", "GET")
                .allowedOrigins("*");
    }
}
