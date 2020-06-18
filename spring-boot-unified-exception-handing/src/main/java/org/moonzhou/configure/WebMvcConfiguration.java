package org.moonzhou.configure;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.moonzhou.constant.ErrorCodeEnum;
import org.moonzhou.exception.version2.AppException;
import org.moonzhou.i18n.I18nService;
import org.moonzhou.result.BaseResult;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 通用的web配置
 * 如：添加拦截器
 *
 * @author moon-zhou <ayimin1989@163.com>
 * @version V1.0.0
 * @description
 * @date 2020/6/6 20:45
 * @since 1.0
 */
@Slf4j
@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
        localeChangeInterceptor.setParamName("language");
        return localeChangeInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
    }

    /*@Override
    public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> exceptionResolvers) {


        exceptionResolvers.add((request, response, handler, e) -> {

            BaseResult baseResult = new BaseResult(ErrorCodeEnum.SYSTEM_ERROR, null);

            if (e instanceof AppException) {
                log.error("configureHandlerExceptionResolvers version2 AppException.", e);
                baseResult = new BaseResult(ErrorCodeEnum.SYSTEM_ERROR, e.getMessage() + "configureHandlerExceptionResolvers 版本二异常");

            } else if (e instanceof org.moonzhou.exception.version3.AppException) {
                log.error("configureHandlerExceptionResolvers version3 AppException.", e);
                baseResult = new BaseResult(ErrorCodeEnum.SYSTEM_ERROR, e.getMessage() + "configureHandlerExceptionResolvers 版本三异常");

            } else if (e instanceof NullPointerException) {
                log.error("configureHandlerExceptionResolvers NullPointerException.", e);
                baseResult = new BaseResult(ErrorCodeEnum.SYSTEM_ERROR, "request error:" + response.getStatus(),
                        "globalNPEHandle: configureHandlerExceptionResolvers" + e.getMessage());
            } else {
                log.error("configureHandlerExceptionResolvers otherException.", e);

                String message;
                if (handler instanceof HandlerMethod) {
                    HandlerMethod handlerMethod = (HandlerMethod) handler;
                    message = String.format("接口 [%s] 出现异常，方法：%s.%s，异常摘要：%s",
                            request.getRequestURI(),
                            handlerMethod.getBean().getClass().getName(),
                            handlerMethod.getMethod().getName(),
                            e.getMessage());
                } else {
                    message = e.getMessage();
                }

                baseResult = new BaseResult(ErrorCodeEnum.SYSTEM_ERROR, "request error:" + response.getStatus(),
                        "globalExceptionHandle: configureHandlerExceptionResolvers" + message);
            }

            responseResult(response, baseResult);

            return new ModelAndView();
        });


    }

    // 处理响应数据格式
    private void responseResult(HttpServletResponse response, BaseResult result) {
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-type", "application/json;charset=UTF-8");
        response.setStatus(200);
        try {
            response.getWriter().write(JSON.toJSONString(result));
        } catch (IOException ex) {
            log.error(ex.getMessage());
        }
    }*/


}
