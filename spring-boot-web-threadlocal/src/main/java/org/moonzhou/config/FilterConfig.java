package org.moonzhou.config;

//import org.moonzhou.filter.manual.LogFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 手动配置过滤器
 */
/*
@Configuration
public class FilterConfig {

    @Autowired
    private LogFilter logFilter;

    @Bean
    public FilterRegistrationBean registerAuthFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(logFilter);
        registration.addUrlPatterns("/*");
        registration.setName("authFilter");
        registration.setOrder(2);  //值越小，Filter越靠前。
        return registration;
    }
    
    //如果有多个Filter，再写一个public FilterRegistrationBean registerOtherFilter(){...}即可。
}*/
