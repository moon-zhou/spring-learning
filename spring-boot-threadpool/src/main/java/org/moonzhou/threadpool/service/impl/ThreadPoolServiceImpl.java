package org.moonzhou.threadpool.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.moonzhou.threadpool.service.ThreadPoolService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * thread pool test service implementation
 * @author moonzhou
 */
@Slf4j
@Service
public class ThreadPoolServiceImpl implements ThreadPoolService {
    @Async
    @Override
    public Future<Boolean> testDefaultThreadPool() {
        log.info("service testDefaultThreadPool in.");

        // mock biz running
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            log.error("testDefaultThreadPool error: ", e);

            throw new RuntimeException(e);
        }

        log.info("service testDefaultThreadPool out.");

        return CompletableFuture.completedFuture(true);
    }

    @Async("asyncTaskExecutor")
    @Override
    public Future<Boolean> testCustomizedThreadPool() {
        log.info("service testCustomizedThreadPool in.");

        // mock biz running
        try {
            TimeUnit.SECONDS.sleep(6);
        } catch (InterruptedException e) {
            log.error("testCustomizedThreadPool error: ", e);

            throw new RuntimeException(e);
        }

        log.info("service testCustomizedThreadPool out.");

        return CompletableFuture.completedFuture(true);
    }
}
