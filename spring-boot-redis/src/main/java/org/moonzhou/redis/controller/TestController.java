package org.moonzhou.redis.controller;

import lombok.extern.slf4j.Slf4j;
import org.moonzhou.redis.dto.Result;
import org.moonzhou.redis.service.SerialNum;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用于测试服务，对外暴露的接口类
 *
 * @author moonzhou
 */
@Slf4j
@RestController
@RequestMapping("/api/v1/test")
public class TestController {

    /**
     * 测试获取流水号，可以通过jmeter进行并发测试
     * http://localhost:8081/api/v1/test/testSerialNum
     * @return 生成的流水号
     */
    @GetMapping("/testSerialNum")
    public Result<String> findById() {

        return Result.success(SerialNum.getFormatSerialNum());
    }
}
