/*
 * Copyright (C), 2002-2020, moon-zhou
 * FileName: LogFilter.java
 * Author:   moon-zhou
 * Email:    ayimin1989@163.com
 * Date:     2020/9/25 16:45
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名    修改时间    版本号       描述
 */
package org.moonzhou.filter.manual;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;

/**
 * 功能描述: 模拟请求入口日志拦截器<br>
 *     手动配置filter
 *
 * @author moon-zhou
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
/*@Slf4j
@Component
public class LogFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("LogFilter init...");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        log.debug("start to log start...");

        log.info("LogFilter doFilter start...");
        filterChain.doFilter(servletRequest, servletResponse);
        log.info("LogFilter doFilter end...");

    }

    @Override
    public void destroy() {
        log.info("LogFilter destroy...");
    }
}*/
