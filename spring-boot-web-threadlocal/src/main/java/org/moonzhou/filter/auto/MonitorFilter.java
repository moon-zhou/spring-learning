/*
 * Copyright (C), 2002-2020, moon-zhou
 * FileName: AuthFilter.java
 * Author:   moon-zhou
 * Email:    ayimin1989@163.com
 * Date:     2020/9/24 18:39
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名    修改时间    版本号       描述
 */
package org.moonzhou.filter.auto;

import lombok.extern.slf4j.Slf4j;
import org.moonzhou.constant.GlobalConstant;
import org.moonzhou.constant.ParamsConstant;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * 功能描述: 模拟监控拦截<br>
 * <p>
 * 通过springboot自动扫描
 *
 * @author moon-zhou
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@Slf4j
@WebFilter(filterName = "monitorFilter", urlPatterns = "/*")
@Order(1) //测试好像这个参数不生效，实际生效的是Filter扫描到的顺序（所以起名很重要）
public class MonitorFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("MonitorFilter init...");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.debug("start to monitor request validate...");

        // 添加该filter自定义的参数
        servletRequest.setAttribute(ParamsConstant.MONITOR_FILTER_PARAM_KEY_NAME, "hello monitor filter");

        GlobalConstant.CURRENT_THREAD_REQUEST.set(servletRequest);

        log.info("MonitorFilter doFilter start...");
        filterChain.doFilter(servletRequest, servletResponse);
        log.info("MonitorFilter doFilter end...");
    }

    @Override
    public void destroy() {
        log.info("MonitorFilter destroy...");
    }
}
