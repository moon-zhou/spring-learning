package org.moonzhou.aspectj.config;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * runtime monitor aspect
 * @author moon zhou
 * @email ayimin1989@163.com
 * @since 2022-04-24
 */
@Aspect
@Component
@Slf4j
public class RunTimeAspect {

    /**
     * description:
     * Pointcut within all controller
     * @return {@link Void}
     */
    @Pointcut("@within(org.springframework.stereotype.Controller) "
            + "|| @within(org.springframework.web.bind.annotation.RestController)"
            + "|| @within(org.springframework.stereotype.Service) ")
    public void aroundPointCut() {
        //Pointcut
    }

    /**
     * description:
     * @param proceedingJoinPoint ProceedingJoinPoint
     * @return {@link Void}
     */
    @Around(value = "aroundPointCut()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) {
        String className = proceedingJoinPoint.getTarget().getClass().getName();
        String methodName = proceedingJoinPoint.getSignature().getName();

        if (className.contains("Controller")) {
            log.info("===================================" + className + "_" + methodName + ": start");
        } else {
            log.info("***********************************" + className + "_" + methodName + ": start");
        }

        long beginTime = System.currentTimeMillis();

        Object result = null;
        try {
            // print param in
            /*Object[] args = proceedingJoinPoint.getArgs();
            for (Object arg : args) {
                if (arg instanceof Map) {
                    // json
                } else {

                }
            }*/

            result = proceedingJoinPoint.proceed();

            // print result
            /*if (result instanceof Map) {
                // json
            } else {

            } */
        } catch (Throwable e) {
            log.error("monitor error: ", e);
        } finally {
            long time = System.currentTimeMillis() - beginTime;

            if (className.contains("Controller")) {
                log.info("===================================" + className + "_" + methodName + ": " + time);
            } else {
                log.info("***********************************" + className + "_" + methodName + ": " + time);
            }
        }

        return result;
    }

}
