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
package org.moonzhou.spring.aop.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * 功能描述: 切面处理<br>
 *
 * 此示例：切点统一定义，然后统一调用
 * @author moon-zhou
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@Component
@Aspect
public class MoonLogAspectV2 {

    /**
     * 可以统一定义切点
     */
    @Pointcut("@annotation(org.moonzhou.spring.aop.annotation.MoonLog)")
    public void pointcut() {

    }


    /**
     * 前置通知
     * @Before 注解表示这是一个前置通知，即在目标方法执行之前执行，注解中，需要填入切点。同时需要注意注解值为全路径。
     *
     * @param joinPoint 包含了目标方法的关键信息
     */
    @Before(value="pointcut()")
    public void before(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        String name = signature.getName();

        System.out.println(name + " 方法开始执行了 V2......");
    }

    /**
     * 后置通知
     * @After 表示这是一个后置通知，即在目标方法执行之后执行
     *
     * @param joinPoint
     */
    @After("pointcut()")
    public void after(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        String name = signature.getName();

        System.out.println(name + " 方法执行结束了 V2......");
    }

    /**
     * 返回通知
     * @AfterReturning 表示这是一个返回通知，即有目标方法有返回值的时候才会触发，
     * 该注解中的 returning 属性表示目标方法返回值的变量名，这个需要和参数一一对应吗
     * 注意：目标方法的返回值类型要和这里方法返回值参数的类型一致，否则拦截不到，如果想拦截所有（包括返回值为 void），则方法返回值参数可以为 Object
     *
     * @param joinPoint
     * @param returnValue
     */
    @AfterReturning(value = "pointcut()", returning = "returnValue")
    public void returning(JoinPoint joinPoint, Integer returnValue) {
        Signature signature = joinPoint.getSignature();
        String name = signature.getName();

        System.out.println(name + " 方法返回值 V2： " + returnValue);
    }

    /**
     * 异常通知
     * 目标方法所抛出的异常，注意，这个参数必须是目标方法所抛出的异常或者所抛出的异常的父类，只有这样，才会捕获。如果想拦截所有，参数类型声明为 Exception
     *
     * @param joinPoint
     * @param e
     */
    @AfterThrowing(value = "pointcut()", throwing = "e")
    public void afterThrowing(JoinPoint joinPoint, Exception e) {
        Signature signature = joinPoint.getSignature();
        String name = signature.getName();

        System.out.println(name + " 方法抛出异常 V2： " + e.getMessage());
    }

    /**
     * 环绕通知
     * 环绕通知是集大成者，可以用环绕通知实现上面的四个通知，这个方法的核心有点类似于在这里通过反射执行方法
     * @param proceedingJoinPoint
     * @return 注意这里的返回值类型最好是 Object ，和拦截到的方法相匹配
     */
    @Around(value="pointcut()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) {

        Object proceed = null;
        try {
            proceed = proceedingJoinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

        return proceed;
    }
}
