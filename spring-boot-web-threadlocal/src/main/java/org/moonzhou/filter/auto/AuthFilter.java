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
import org.apache.commons.lang3.StringUtils;
import org.moonzhou.constant.GlobalConstant;
import org.moonzhou.constant.ParamsConstant;
import org.moonzhou.dto.LogDto;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.util.UUID;

/**
 * 功能描述: 模拟鉴权拦截<br>
 * <p>
 * 通过springboot自动扫描
 *
 * @author moon-zhou
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@Slf4j
@WebFilter(filterName = "authFilter", urlPatterns = "/*")
@Order(2) //测试好像这个参数不生效，实际生效的是Filter扫描到的顺序（所以起名很重要）
public class AuthFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("AuthFilter init...");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.debug("start to auth request validate...");

        // servletRequest与线程一一关联，引用不会变
        //log.info("--------servletRequest:" + servletRequest);

        // 判断ServletRequest是否已经有值（即使修改了tomcat的最大线程数，调整的很小，正常通过浏览器访问，也无法重现这种情，使用jmeter进行模拟高并发压测，有部分请求是可以进入）
        /*ServletRequest request = GlobalConstant.CURRENT_THREAD_REQUEST.get();
        if (null != request && StringUtils.isNotBlank((String)request.getAttribute(ParamsConstant.AUTH_FILTER_PARAM_KEY_NAME))) {
            log.info("request param is not null -------------");
        } else {
            log.info("auth request==" + request);
            if (null != request) {
                log.info("auth param==" + request.getAttribute(ParamsConstant.AUTH_FILTER_PARAM_KEY_NAME));
            }
        }*/

        // 添加该filter自定义的参数，设置到ThreadLocal变量
        servletRequest.setAttribute(ParamsConstant.AUTH_FILTER_PARAM_KEY_NAME, "hello auth filter" + System.currentTimeMillis());
        GlobalConstant.CURRENT_THREAD_REQUEST.set(servletRequest);

        // 设置日志id
        LogDto logDtoThreadLocal = GlobalConstant.LOG.get();
        if (logDtoThreadLocal == null || StringUtils.isBlank(logDtoThreadLocal.getId())) {
            LogDto logDto = new LogDto();
            String id = UUID.randomUUID().toString();
            logDto.setId(id);
            GlobalConstant.LOG.set(logDto);

            log.info("create log id： " + id);
        }

        log.info("AuthFilter doFilter start...");
        filterChain.doFilter(servletRequest, servletResponse);
        log.info("AuthFilter doFilter end...");

        // 结束之后删除，避免下一次进来还有值
        GlobalConstant.LOG.remove();
    }

    @Override
    public void destroy() {
        log.info("AuthFilter destroy...");
    }
}
