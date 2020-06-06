package org.moonzhou.web;

import org.apache.commons.lang3.StringUtils;
import org.moonzhou.exception.asserter.ResponseEnum;
import org.moonzhou.service.ITestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author moon-zhou <ayimin1989@163.com>
 * @version V1.0.0
 * @description
 * @date 2020/6/6 11:35
 * @since 1.0
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private ITestService testService;

    /**
     * http://localhost:8080/test/index
     *
     * @return
     */
    @RequestMapping("/index")
    String index() {
        return "Hello World!";
    }

    /**
     * http://localhost:8080/test/testString
     *
     * @return
     */
    @RequestMapping("/testString")
    String testString() {
        return testService.getTestString();
    }

    /**
     * http://localhost:8080/test/testMap
     *
     * @return
     */
    @RequestMapping("testMap")
    Map<String, Object> testMap() {
        return testService.getTestMap();
    }

    /**
     * http://localhost:8080/test/testNPE
     *
     * @return
     */
    @RequestMapping("testNPE")
    String testNPE() {
        throw new NullPointerException("test NPE!!!");
    }

    /**
     * http://localhost:8080/test/testRuntimeException
     *
     * @return
     */
    @RequestMapping("testRuntimeException")
    String testRuntimeException() {
        throw new RuntimeException("test runtime exception!!!");
    }

    /**
     * http://localhost:8080/test/testAssert01Exception/0
     * http://localhost:8080/test/testAssert01Exception/1
     * @param id
     * @return
     */
    @RequestMapping("testAssert01Exception/{id}")
    Map<String, Object> testAssert01Exception(@PathVariable("id") String id) {

        if (StringUtils.equals("0", id)) {
            id = "";
            ResponseEnum.PARAM_NOT_NULL.assertNotBlank(id);
        }

        return testService.getTestMap();
    }
}
