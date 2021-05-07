/*
 * Copyright (C), 2002-2021, moon-zhou
 * FileName: MoonLogAspectV2.java
 * Author:   moon-zhou
 * Email:    ayimin1989@163.com
 * Date:     2021/4/16 16:40
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名    修改时间    版本号       描述
 */
package org.moonzhou.offline.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.http.HttpServletRequest;

/**
 * 功能描述: 注解方式切面处理<br>
 *
 * @author moon-zhou
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@Component
@Aspect
public class AOfflineAspect {

    /**
     * 统一定义切点
     */
    @Pointcut("@annotation(org.moonzhou.offline.annotation.Offline)")
    public void pointcut() {

    }


    /**
     * 前置通知：判断方法上注解值，如果为true，表示已经下线，否则表示服务未下线
     * @Before 注解表示这是一个前置通知，即在目标方法执行之前执行，注解中，需要填入切点。同时需要注意注解值为全路径。
     *
     * @param joinPoint 包含了目标方法的关键信息
     */
    @Before(value="pointcut()")
    public void before(JoinPoint joinPoint) {

        //获取RequestAttributes
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        //从获取RequestAttributes中获取HttpServletRequest的信息
        HttpServletRequest request = (HttpServletRequest) requestAttributes.resolveReference(RequestAttributes.REFERENCE_REQUEST);

        System.out.println("=================" + request.getRequestURI());

        // TODO 获取注解值，同时抛送运行时异常，表达提交和ajax提交的异常进行区分处理
    }

}
