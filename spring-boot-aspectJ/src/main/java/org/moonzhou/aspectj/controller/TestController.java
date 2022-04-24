package org.moonzhou.aspectj.controller;

import org.moonzhou.aspectj.base.Result;
import org.moonzhou.aspectj.dto.ProcessDto;
import org.moonzhou.aspectj.service.TestService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author  moon zhou
 * @description
 * @email ayimin1989@163.com
 * @date 2022/4/24 11:03
 **/
@RestController
@RequestMapping("/test")
public class TestController {

    /**
     * service, 通过构造函数注入
     */
    private final TestService testService;

    public TestController(TestService testService) {
        this.testService = testService;
    }

    /**
     * http://localhost:8081/test/index
     *
     * @return
     */
    @RequestMapping("/index")
    public String index() {
        return "hello spring boot aspectJ demo project!!!";
    }

    /**
     * http://localhost:8081/test/findProcessById/123
     *
     * @return
     */
    @RequestMapping("/findProcessById/{id}")
    @ResponseBody
    public Result<ProcessDto> findProcessById(@PathVariable String id) {
        ProcessDto process = testService.findProcessById(id);

        return Result.success(process);
    }

    /**
     * http://localhost:8081/test/process
     *
     * @return
     */
    @RequestMapping("/process")
    @ResponseBody
    public ProcessDto findProcessById() {

        return testService.process();
    }
}
