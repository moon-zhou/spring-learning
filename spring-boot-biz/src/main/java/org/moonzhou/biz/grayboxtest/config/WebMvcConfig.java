package org.moonzhou.biz.grayboxtest.config;

import org.moonzhou.biz.grayboxtest.backend.service.GrayBoxTestService;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * WebMvcConfig
 * @author moon zhou
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Bean
    public FilterRegistrationBean<GrayBoxTestFilter> grayBoxTestFilter(GrayBoxTestService grayBoxTestService) {
        FilterRegistrationBean<GrayBoxTestFilter> filterFilterRegistrationBean = new FilterRegistrationBean<>(new GrayBoxTestFilter(grayBoxTestService));
        // 实际业务过程中，该过滤器需要在SSO的过滤器之后，因为灰度测试，需要获取用户信息，比对用户信息是否在灰度名单之中
        filterFilterRegistrationBean.addUrlPatterns("/api/v1/biz/*");
        filterFilterRegistrationBean.setOrder(0);
        return filterFilterRegistrationBean;
    }
}
