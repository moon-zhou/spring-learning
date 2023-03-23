package org.moonzhou.threadpool.service;

import java.util.concurrent.Future;

/**
 * test thread pool service interface
 * @author moonzhou
 */
public interface ThreadPoolService {

    /**
     * test {@link org.springframework.scheduling.annotation.Async} default thread pool
     * @return true: biz success
     */
    Future<Boolean> testDefaultThreadPool();

    /**
     * test {@link org.springframework.scheduling.annotation.Async} customized thread pool
     * @return true: biz success
     */
    Future<Boolean> testCustomizedThreadPool();

    /**
     * test {@link org.springframework.scheduling.annotation.Async} customized thread pool
     * @return true: biz success
     */
    Future<Boolean> testMessageThreadPool();

    /**
     * test thread pool exception
     */
    void testException();
}
