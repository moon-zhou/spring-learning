package org.moonzhou.threadpool.actuator.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@Configuration
@EnableAsync
public class ThreadPoolConfig {

    // public static final ThreadPoolExecutor executor =
    // 		new ThreadPoolExecutor(5, 10, 1, TimeUnit.MINUTES, new ArrayBlockingQueue<Runnable>(100));

    @Bean(name = "asyncTaskExecutor")
    public ThreadPoolExecutor asyncTaskExecutor() {
        final AtomicInteger threadNum = new AtomicInteger(1);
        ThreadPoolExecutor taskExecutor = new ThreadPoolExecutor(10, 20, 1, TimeUnit.MINUTES,
                new ArrayBlockingQueue<Runnable>(50),
                r -> {
                    Thread t = new Thread(r, "moon-thread-" + threadNum.getAndIncrement());

                    log.info("{} has been created.", t.getName());
                    return t;
                },
                new ThreadPoolExecutor.CallerRunsPolicy());

        return taskExecutor;
    }
}