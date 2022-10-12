package org.moonzhou.event.aop;

import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.moonzhou.event.annotation.AsyncMethod;
import org.moonzhou.event.annotation.Type;
import org.moonzhou.event.event.LogEvent;
import org.moonzhou.event.param.LogParam;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author moon zhou
 * @version 1.0
 * @description: async aspect: before method, after method
 * @date 2022/10/11 17:47
 */
@Aspect
@Component
@AllArgsConstructor
public class AsyncAspect {

    private final ApplicationContext applicationContext;

    @Pointcut("@annotation(org.moonzhou.event.annotation.AsyncMethod)")
    public void pointcut() {

    }

    @Before("pointcut()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {


    }

    /**
     * 后置通知
     * @After 表示这是一个后置通知，即在目标方法执行之后执行
     *
     * @param joinPoint
     */
    @After("pointcut()")
    public void after(JoinPoint joinPoint) throws Throwable {
        Signature signature = joinPoint.getSignature();

        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        AsyncMethod asyncMethod = method.getAnnotation(AsyncMethod.class);

        // not after, return
        Type type = asyncMethod.type();
        if (!StringUtils.equals(type.getValue(), Type.AFTER.getValue())) {
            return;
        }

        // ergodic all event, process the annotation one
        Class<?>[] events = asyncMethod.value();
        for (Class<?> event : events) {
            if (StringUtils.equals(LogEvent.class.getName(), event.getName())) {
                Object[] args = joinPoint.getArgs();
                if (null != args) {
                    for (Object arg : args) {
                        if (arg instanceof LogParam) {
                            LogEvent logEvent = new LogEvent((LogParam) arg);

                            applicationContext.publishEvent(logEvent);
                        }
                    }
                }

            }
        }


        String name = signature.getName();

    }
}


