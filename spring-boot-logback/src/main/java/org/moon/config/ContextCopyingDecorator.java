package org.moon.config;

import com.sun.istack.internal.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.core.task.TaskDecorator;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Map;
import java.util.function.Consumer;

public class ContextCopyingDecorator implements TaskDecorator {

    private static final Logger LOGGER = LoggerFactory.getLogger("mdc");

    @NotNull
    @Override
    public Runnable decorate(@NotNull Runnable runnable) {
        try {
            // 需要对 RequestAttributes 进行深拷贝才可以
            // RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
            Map<String, String> previous = MDC.getCopyOfContextMap();
            SecurityContext securityContext = SecurityContextHolder.getContext();
            return () -> {
                try {
                    // ifNotNullThen(requestAttributes, RequestContextHolder::setRequestAttributes);
                    ifNotNullThen(previous, MDC::setContextMap);
                    ifNotNullThen(securityContext, SecurityContextHolder::setContext);
                    runnable.run();
                } finally {
                    // ifNotNullThen(requestAttributes RequestContextHolder::resetRequestAttributes);
                    ifNotNullThen(previous, MDC::clear);
                    ifNotNullThen(securityContext, SecurityContextHolder::clearContext);
                }
            };
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return runnable;
        }
    }

    private <T> void ifNotNullThen(T t, Consumer<T> then) {
        if (t != null) {
            then.accept(t);
        }
    }

    private <T> void ifNotNullThen(T t, Runnable runnable) {
        if (t != null) {
            runnable.run();
        }
    }
}
