package org.moon.config;

import org.moon.util.MDCUtil;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author moon zhou
 * @version 1.0
 * @description: log back optimize: add requestId, user id in MDC
 * @date 2022/9/30 14:00
 */
public class LogBackOptimizeInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        MDCUtil.setTraceId();
        MDCUtil.setUserInfo();

        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
        MDCUtil.removeTraceId();
        MDCUtil.removeUserInfo();
    }
}
