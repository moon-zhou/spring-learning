package org.moon.config;

import org.springframework.boot.task.TaskExecutorBuilder;
import org.springframework.boot.task.TaskExecutorCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.time.Duration;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * WebMvcConfig
 * @author moon zhou
 */
@Configuration
@EnableAsync
public class WebMvcConfig implements WebMvcConfigurer {

    /**
     * description: addInterceptors
     * @param registry InterceptorRegistry
     * @return {@link Void}
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        WebMvcConfigurer.super.addInterceptors(registry);
        registry.addInterceptor(logBackOptimizeInterceptor()).addPathPatterns("/**");
    }

    /**
     * interceptor to set mdc
     * @return
     */
    @Bean
    public LogBackOptimizeInterceptor logBackOptimizeInterceptor() {
        return new LogBackOptimizeInterceptor();
    }

    /**
     * thread pool
     * @return
     */
    @Bean(name = "asyncTaskExecutor")
    public Executor asyncTaskExecutor() {
        TaskExecutorCustomizer rejectHandlerCustomizer =
                taskExecutor -> taskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());

        int availableProcessors = Runtime.getRuntime().availableProcessors();

        TaskExecutorBuilder builder = new TaskExecutorBuilder();
        builder = builder.corePoolSize(availableProcessors);
        builder = builder.maxPoolSize(availableProcessors);
        builder = builder.queueCapacity(100);
        builder = builder.allowCoreThreadTimeOut(true);
        builder = builder.keepAlive(Duration.ofSeconds(60));
        builder = builder.awaitTermination(false);
        builder = builder.threadNamePrefix("MDC-SUB-THREAD");
        builder = builder.taskDecorator(new ContextCopyingDecorator());
        builder = builder.customizers(rejectHandlerCustomizer);
        return builder.build();
    }
}
