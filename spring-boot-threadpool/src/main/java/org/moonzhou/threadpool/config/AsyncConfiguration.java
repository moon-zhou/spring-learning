package org.moonzhou.threadpool.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurerSupport;

import java.lang.reflect.Method;
import java.util.Arrays;

@Slf4j
@Configuration
public class AsyncConfiguration extends AsyncConfigurerSupport {
    @Override
    public AsyncUncaughtExceptionHandler
    getAsyncUncaughtExceptionHandler() {
        return new AsyncUncaughtExceptionHandler() {

            @Override
            public void handleUncaughtException(Throwable ex,
                                                Method method, Object... params) {
                // Just log. Actual processing according to business requirements.
                log.error("\r\n>>> CustomAsyncUncaughtExceptionHandler,class:{}, method: {}, params: {}, error: {}",
                        method.getDeclaringClass().getSimpleName(), method.getName(), Arrays.toString(params),
                        ex.getMessage());
                log.error("exception stack: ", ex);
            }
        };
    }
}