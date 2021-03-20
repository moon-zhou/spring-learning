/*
 * Copyright (C), 2002-2021, moon-zhou
 * FileName: CookieProducerInterceptor.java
 * Author:   moon-zhou
 * Email:    ayimin1989@163.com
 * Date:     2021/3/20 9:05
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名    修改时间    版本号       描述
 */
package org.moonzhou.xss.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

/**
 * 功能描述: 判断是否生产cookie拦截器<br>
 *
 * @author moon-zhou
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@Slf4j
@Component
public class CookieProducerInterceptor implements HandlerInterceptor {

    /**
     * 存储在cookie里的登录会话id
     */
    private static final String LOGIN_COOKIE_KEY = "loginId";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        Cookie[] cookies = request.getCookies();
        Map<String, String> cookieMap = new HashMap<>();

        if (null != cookies) {
            for (int i = 0; i < cookies.length; i++) {
                Cookie cookie = cookies[i];
                cookieMap.put(cookie.getName(), cookie.getValue());
            }
        }

        // 如果cookie里没有登录的会话id，则模拟生成一个
        Set<String> cookieKeys = cookieMap.keySet();
        if (null != cookieKeys && !cookieKeys.contains(LOGIN_COOKIE_KEY)) {

            // 生成登录Cookie，设置过期日期为24小时
            String loginId = UUID.randomUUID().toString();
            Cookie loginCookie = new Cookie(LOGIN_COOKIE_KEY, loginId);
            loginCookie.setMaxAge(60 * 60 * 24);

            response.addCookie(loginCookie);

            log.info("无登录cookie，模拟生成，loginId为: {}...", loginId);
        }

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
