package org.moon.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * WebMvcConfig
 * @author moon zhou
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    /**
     * description: addInterceptors
     * @param registry InterceptorRegistry
     * @return {@link Void}
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        WebMvcConfigurer.super.addInterceptors(registry);
        registry.addInterceptor(logBackOptimizeInterceptor()).addPathPatterns("/**");
    }

    @Bean
    public LogBackOptimizeInterceptor logBackOptimizeInterceptor() {
        return new LogBackOptimizeInterceptor();
    }
}
