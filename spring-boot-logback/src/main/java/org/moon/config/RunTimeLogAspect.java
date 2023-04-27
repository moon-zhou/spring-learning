package org.moon.config;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.moon.annotation.MoonExceptionLog;
import org.moon.util.RunTimeLogUtil;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

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

        RunTimeLogUtil.logStart(className, methodName);

        long beginTime = System.currentTimeMillis();

        Object result = null;
        try {
            // print log
            Object[] args = proceedingJoinPoint.getArgs();
            RunTimeLogUtil.logParam(className, methodName, args);

            // do
            result = proceedingJoinPoint.proceed();

            // print log
            RunTimeLogUtil.logResult(className, methodName, result);

        } catch (Throwable e) {
            // just log, throw up
            log.error("monitor error: ", e);
            throw e;
        } finally {
            long time = System.currentTimeMillis() - beginTime;

            RunTimeLogUtil.logEnd(className, methodName, time);
        }

        return result;
    }

    ///////////////////////////////////////exception log by annotation////////////////////////////////////////////////
    @Pointcut("@annotation(org.moon.annotation.MoonExceptionLog)")
    public void exceptionLog() {

    }

    /**
     * test method throw Runtime Exception
     * @param joinPoint
     * @param e
     */
    @AfterThrowing(value = "exceptionLog()", throwing = "e")
    public void afterThrowingRuntimeException(JoinPoint joinPoint, RuntimeException e) {
        Signature signature = joinPoint.getSignature();

        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();

        log.error("runtime exception: ------------------>");
        // log.error("method {} error, throw runtime exception: ", method.getName(), e);
    }

    /**
     * 异常通知
     * 目标方法所抛出的异常，注意，这个参数必须是目标方法所抛出的异常或者所抛出的异常的父类，只有这样，才会捕获。如果想拦截所有，参数类型声明为 Exception
     *
     * @param joinPoint
     * @param e
     */
    @AfterThrowing(value = "exceptionLog()", throwing = "e")
    public void afterThrowing(JoinPoint joinPoint, Throwable e) throws Throwable {
        log.error("throwable: ------------------>");
        Signature signature = joinPoint.getSignature();

        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();

        // handle exception log
        boolean exceptionLog = method.isAnnotationPresent(MoonExceptionLog.class);
        if (exceptionLog) {
            MoonExceptionLog moonExceptionLog = method.getAnnotation(MoonExceptionLog.class);
            Class<? extends Throwable>[] logException = moonExceptionLog.logFor();

            boolean logFlag = false;
            if (null != logException) {
                for (Class<? extends Throwable> exception : logException) {
                    if (e.getClass().equals(exception)) {
                        log.error("exception to log: ", e);
                        logFlag = true;
                        break;
                    }
                }
            }

            log.info("in log aspect ");
        }
    }
}
