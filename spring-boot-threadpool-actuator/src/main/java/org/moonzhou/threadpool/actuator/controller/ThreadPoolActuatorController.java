package org.moonzhou.threadpool.actuator.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @author moon zhou
 * @description
 * @email ayimin1989@163.com
 * @date 2022/6/15 21:30
 **/
@Slf4j
@RestController
@RequestMapping("/threadpool-actuator")
public class ThreadPoolActuatorController {


    /**
     * http://localhost:8080/threadpool-actuator/index
     *
     * @return
     */
    @RequestMapping("/index")
    public String index() {
        return "hello spring boot thread pool actuator demo project!!!";
    }

    /**
     * http://localhost:8080/threadpool-actuator/run-biz
     * @return
     */
    @Async("asyncTaskExecutor")
    @RequestMapping("/run-biz")
    public String runBiz() {

        log.info("biz running...");

        // mock biz running
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return "biz success!!!";
    }

}
