package org.moonzhou.threadpool.controller;

import lombok.extern.slf4j.Slf4j;
import org.moonzhou.threadpool.service.ThreadPoolService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.ExecutionException;

/**
 * @author moon zhou
 * @description
 * @email ayimin1989@163.com
 * @date 2022/6/15 21:30
 **/
@Slf4j
@RestController
@RequestMapping("/threadpool")
public class ThreadPoolController {

    private final ThreadPoolService threadPoolService;

    public ThreadPoolController(ThreadPoolService threadPoolService) {
        this.threadPoolService = threadPoolService;
    }

    /**
     * http://localhost:8081/threadpool/index
     *
     * @return
     */
    @RequestMapping("/index")
    public String index() {
        return "hello spring boot thread pool demo project!!!";
    }

    /**
     * http://localhost:8081/threadpool/testDefaultThreadPool
     *
     * @return
     */
    @RequestMapping("/testDefaultThreadPool")
    public String testDefaultThreadPool(HttpServletResponse response) {

        log.info("controller testDefaultThreadPool in.");

        threadPoolService.testDefaultThreadPool();

        log.info("controller testDefaultThreadPool out. and return fixed value.");

        return "success";
    }

    /**
     * http://localhost:8081/threadpool/testDefaultThreadPoolWithResult
     *
     * @return
     */
    @RequestMapping("/testDefaultThreadPoolWithResult")
    public String testDefaultThreadPoolWithResult(HttpServletResponse response) throws ExecutionException, InterruptedException {

        log.info("controller testDefaultThreadPoolWithResult in.");

        Boolean asyncResult = threadPoolService.testDefaultThreadPool().get();

        log.info("controller testDefaultThreadPoolWithResult out. and return runtime value: {}.", asyncResult);

        return "success";
    }


    /**
     * http://localhost:8081/threadpool/testCustomizedThreadPool
     *
     * @return
     */
    @RequestMapping("/testCustomizedThreadPool")
    public String testCustomizedThreadPool(HttpServletResponse response) {

        log.info("controller testCustomizedThreadPool in.");

        threadPoolService.testCustomizedThreadPool();

        log.info("controller testCustomizedThreadPool out. and return fixed value.");

        return "success";
    }

    /**
     * http://localhost:8081/threadpool/testCustomizedThreadPoolWithResult
     *
     * @return
     */
    @RequestMapping("/testCustomizedThreadPoolWithResult")
    public String testCustomizedThreadPoolWithResult(HttpServletResponse response) throws ExecutionException, InterruptedException {

        log.info("controller testCustomizedThreadPoolWithResult in.");

        Boolean asyncResult = threadPoolService.testCustomizedThreadPool().get();

        log.info("controller testCustomizedThreadPoolWithResult out. and return runtime value: {}.", asyncResult);

        return "success";
    }
}
