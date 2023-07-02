package org.moonzhou.fiaa.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;


@Slf4j
@Aspect
@Component
public class MyAop {

    /**
     * 仅匹配getAop这一个方法
     */
    @Pointcut("execution(* org.moonzhou.fiaa.controller.MyController.getAop())")
    public void pointcut() {

    }

    @Before("pointcut()")
    public void before() {
        log.info("aop before");
    }

    @After("pointcut()")
    public void after() {
        log.info("aop after");
    }

    @Pointcut("execution(* org.moonzhou.fiaa.controller.MyController.getAll())")
    public void pointcut2() {

    }

    @Before("pointcut2()")
    public void before2() {
        log.info("aop before 222");
    }

    @After("pointcut2()")
    public void after2() {
        log.info("aop after 222");
    }

}