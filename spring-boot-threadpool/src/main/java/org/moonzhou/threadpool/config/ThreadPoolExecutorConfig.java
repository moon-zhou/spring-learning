package org.moonzhou.threadpool.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.task.TaskExecutorCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author moonzhou
 * @description ThreadPool config for customized async
 **/
@Slf4j
@EnableConfigurationProperties(ThreadPoolConfig.class)
@Configuration
@EnableAsync
public class ThreadPoolExecutorConfig {

    /**
     * 异步线程池处理
     * IO密集型任务  = 一般为2*CPU核心数（常出现于线程中：数据库数据交互、文件上传下载、网络数据传输等等）
     * CPU密集型任务 = 一般为CPU核心数+1（常出现于线程中：复杂算法）
     * 混合型任务  = 视机器配置和复杂度自测而定
     * @return
     */
    @Bean(name = "asyncTaskExecutor")
    public Executor asyncTaskExecutor(ThreadPoolConfig threadPoolConfig) {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();

        // 设置线程池参数信息
        // 核心线程数：线程池创建时候初始化的线程数
        taskExecutor.setCorePoolSize(threadPoolConfig.getCorePoolSize());

        // 最大线程数：线程池最大的线程数，只有在缓冲队列满了之后才会申请超过核心线程数的线程
        taskExecutor.setMaxPoolSize(threadPoolConfig.getMaxPoolSize());

        // 缓冲队列：用来缓冲执行任务的队列
        taskExecutor.setQueueCapacity(threadPoolConfig.getQueueCapacity());

        // 允许线程的空闲时间60秒：当超过了核心线程之外的线程在空闲时间到达之后会被销毁
        taskExecutor.setKeepAliveSeconds(60);

        // 线程池名的前缀：设置好了之后可以方便我们定位处理任务所在的线程池
        taskExecutor.setThreadNamePrefix("moon-1-");

        // 用来设置线程池关闭的时候等待所有任务都完成再继续销毁其他的Bean
        taskExecutor.setWaitForTasksToCompleteOnShutdown(true);
        // 该方法用来设置线程池中任务的等待时间，如果超过这个时候还没有销毁就强制销毁，以确保应用最后能够被关闭，而不是阻塞住。
        taskExecutor.setAwaitTerminationSeconds(60);

        // 修改拒绝策略为使用当前线程执行
        taskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());

        // 初始化线程池
        taskExecutor.initialize();
        return taskExecutor;
    }

    @Bean(name = "asyncMessageExecutor")
    public Executor asyncMessageExecutor(ThreadPoolConfig threadPoolConfig) {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();

        // 设置线程池参数信息
        // 核心线程数：线程池创建时候初始化的线程数
        taskExecutor.setCorePoolSize(threadPoolConfig.getCorePoolSize());

        // 最大线程数：线程池最大的线程数，只有在缓冲队列满了之后才会申请超过核心线程数的线程
        taskExecutor.setMaxPoolSize(threadPoolConfig.getMaxPoolSize());

        // 缓冲队列：用来缓冲执行任务的队列
        taskExecutor.setQueueCapacity(threadPoolConfig.getQueueCapacity());

        // 允许线程的空闲时间60秒：当超过了核心线程之外的线程在空闲时间到达之后会被销毁
        taskExecutor.setKeepAliveSeconds(60);

        // 线程池名的前缀：设置好了之后可以方便我们定位处理任务所在的线程池
        taskExecutor.setThreadNamePrefix("moon-2-");

        // 用来设置线程池关闭的时候等待所有任务都完成再继续销毁其他的Bean
        taskExecutor.setWaitForTasksToCompleteOnShutdown(true);
        // 该方法用来设置线程池中任务的等待时间，如果超过这个时候还没有销毁就强制销毁，以确保应用最后能够被关闭，而不是阻塞住。
        taskExecutor.setAwaitTerminationSeconds(60);

        // 修改拒绝策略为使用当前线程执行
        taskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        /* taskExecutor.setRejectedExecutionHandler(new RejectedExecutionHandler() {
            @Override
            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                // ...MQ/kafka等再单独处理等
            }
        }); */

        // 初始化线程池
        taskExecutor.initialize();
        return taskExecutor;
    }

    @Bean
    public TaskExecutorCustomizer configTaskExecutorCustomizer() {
        int machineCpuCore = Runtime.getRuntime().availableProcessors();
        RejectedExecutionHandler rejectedExecutionHandler = new ThreadPoolExecutor.AbortPolicy();

        return executor -> {
            executor.setCorePoolSize(machineCpuCore);
            executor.setMaxPoolSize(machineCpuCore * 10);
            // executor.setThreadNamePrefix("moon-default-");
            executor.setRejectedExecutionHandler(
                    (r, exe) -> {
                        log.error(
                                "core: {}, max: {}, queue: {}",
                                exe.getCorePoolSize(),
                                exe.getMaximumPoolSize(),
                                exe.getQueue().size());
                        rejectedExecutionHandler.rejectedExecution(r, exe);
                    });
        };
    }

    @Bean
    public TaskExecutorCustomizer monitorTaskExecutorCustomizer() {
        return executor -> {
            try {
                log.info("TaskExecutor - corePoolSize: {}", executor.getCorePoolSize());
                log.info("TaskExecutor - maxPoolSize: {}", executor.getMaxPoolSize());
            } catch (Throwable e) {
                log.error("auditTaskExecutorCustomizer exception", e);
                throw new RuntimeException(e);
            }
        };
    }
}
