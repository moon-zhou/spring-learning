package org.moon.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 切点：自定义注解
 * 方法级，使用了该注解的方法，都需要进行aop拦截处理
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MoonExceptionLog {

    String value() default "";

    /**
     * 需要记录错误日志的异常，不指定则记录所有的异常日志
     * @return
     */
    Class<? extends Throwable>[] logFor() default {};
}