/*
 * Copyright (C), 2002-2021, moon-zhou
 * FileName: RequestFilter.java
 * Author:   moon-zhou
 * Email:    ayimin1989@163.com
 * Date:     2021/7/6 9:36
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名    修改时间    版本号       描述
 */
package org.moonzhou.filter.dispatcher;

import org.moonzhou.filter.webfilter.WebFilter01;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 功能描述: filter dispatcher属性测试拦截器<br>
 *
 * @author moon-zhou
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@WebFilter(urlPatterns = {"/dispatcher/request",
        "/dispatcher/sendRedirect1", "/dispatcher/sendRedirect2",
        "/dispatcher/forward1", "/dispatcher/forward2",
        "/dispatcher/include", "/dispatcher/include1", "/dispatcher/include2"}, dispatcherTypes = {DispatcherType.REQUEST})
public class RequestFilter implements Filter {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebFilter01.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // web.xml里配置filter的参数等
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;

        LOGGER.info("Inside RequestFilter start: {}", req.getRequestURI());
        chain.doFilter(request, response);
        LOGGER.info("Inside RequestFilter end: {}, {}", req.getRequestURI(), response.getContentType());
    }

    @Override
    public void destroy() {

    }
}
