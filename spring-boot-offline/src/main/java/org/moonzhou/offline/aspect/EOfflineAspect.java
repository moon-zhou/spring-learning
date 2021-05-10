/*
 * Copyright (C), 2002-2021, moon-zhou
 * FileName: EOfflineAspect.java
 * Author:   moon-zhou
 * Email:    ayimin1989@163.com
 * Date:     2021/5/10 14:31
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名    修改时间    版本号       描述
 */
package org.moonzhou.offline.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.moonzhou.offline.exception.AjaxOfflineException;
import org.moonzhou.offline.exception.FormOfflineException;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.http.HttpServletRequest;

/**
 * 功能描述: execution配置切入点，来进行http服务接口的下线<br>
 *
 * @author moon-zhou
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@Component
@Aspect
public class EOfflineAspect {

    // 注释掉的切面仅用于测试execution下，所有类，所有方法且任意入参和任意返回的方法，也就是无限制，拦截execution下所有controller的方法
    // 第二个切面只拦截下线的controller下所有方法
    // 第三个切面本次测试切点，因为类里有对比请求，所以将需要下线的方法都以Off结尾
//    @Pointcut("execution(* org.moonzhou.offline.web.execution.*.*(..))")
//    @Pointcut("execution(* org.moonzhou.offline.web.execution.EOfflineController.*(..))")
    @Pointcut("execution(* org.moonzhou.offline.web.execution.EOfflineController.*Off(..))")
    public void pointcut() {
    }

    @Before("pointcut()")
    public void before(JoinPoint joinPoint) {

        // 获取RequestAttributes
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        // 从获取RequestAttributes中获取HttpServletRequest的信息
        HttpServletRequest request = (HttpServletRequest) requestAttributes.resolveReference(RequestAttributes.REFERENCE_REQUEST);

        // TODO 此处可以结合分布式配置中心，配置可下线的requestUri，同时execution配置web目录，可以达到动态配置下线服务的功能

        // 如果是ajax请求
        if ("XMLHttpRequest".equalsIgnoreCase(request.getHeader("x-requested-with"))) {
            throw new AjaxOfflineException("ajax请求：" + request.getRequestURI() + "已下线");
        } else {
            // 表单类请求
            throw new FormOfflineException("表单请求：" + request.getRequestURI() + "已下线");
        }

    }
}
