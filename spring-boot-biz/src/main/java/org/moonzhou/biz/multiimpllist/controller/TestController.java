package org.moonzhou.biz.multiimpllist.controller;

import lombok.extern.slf4j.Slf4j;
import org.moonzhou.biz.multiimpllist.biz.TrafficService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author moon zhou
 * @since 2023-08-23
 */
@Slf4j
@RestController
@RequestMapping("/test")
public class TestController {

    private final TrafficService trafficService;

    public TestController(TrafficService trafficService) {
        this.trafficService = trafficService;
    }

    /**
     * http://localhost:8080/test/test
     * @return
     */
    @GetMapping("/test")
    public Object detail() {
        Object bus = trafficService.traffic("BUS", null);
        log.info("test: {}", bus);
        return bus;
    }

}
