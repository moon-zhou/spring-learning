package org.moon.config;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.moon.base.BaseParam;
import org.moon.base.Result;
import org.moon.util.JsonUtils;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * runtime monitor aspect: param,result and run time
 *
 * @author moon zhou
 */
@Aspect
@Component
@Slf4j
public class RunTimeLogAspect {

    /**
     * description:
     * Pointcut within all controller
     *
     * @return {@link Void}
     */
    @Pointcut("execution(public * org.moon..*Controller.*(..))")
    public void aroundControllerLogPointCut() {
        // Pointcut
    }

    @Pointcut("execution(public * org.moon..*ServiceImpl.*(..))")
    public void aroundServiceLogPointCut() {
        // Pointcut
    }

    /**
     * description:
     *
     * @param proceedingJoinPoint ProceedingJoinPoint
     * @return {@link Void}
     */
    @Around(value = "aroundControllerLogPointCut() || aroundServiceLogPointCut()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        String className = proceedingJoinPoint.getTarget().getClass().getName();
        String methodName = proceedingJoinPoint.getSignature().getName();

        String methodInfo = className + "_" + methodName;
        if (className.contains("Controller")) {
            log.info("===================================" + methodInfo + ": start");
        } else {
            log.info("***********************************" + methodInfo + ": start");
        }

        long beginTime = System.currentTimeMillis();

        Object result = null;
        try {
            // print log
            Object[] args = proceedingJoinPoint.getArgs();
            for (Object arg : args) {
                // 入参是否为 user
                if (arg instanceof BaseParam) {
                    log.info("{} param -------> {}", methodInfo, JsonUtils.toJson(arg));
                } else if (arg instanceof Map) {
                    log.info("{} param -------> {}", methodInfo, JsonUtils.toJson(arg));
                } else {
                    log.info("{} param -------> {}", methodInfo, arg);
                }
            }

            // do
            result = proceedingJoinPoint.proceed();

            // print log
            if (result instanceof Result) {
                log.info("{} result -------> {}", methodInfo, JsonUtils.toJson(result));
            } else if (result instanceof Map) {
                log.info("{} result -------> {}", methodInfo, JsonUtils.toJson(result));
            } else {
                log.info("{} result -------> {}", methodInfo, result);
            }

        } catch (Throwable e) {
            // just log, throw up
            log.error("monitor error: ", e);
            throw e;
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
