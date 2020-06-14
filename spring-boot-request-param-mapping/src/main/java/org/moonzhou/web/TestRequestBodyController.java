package org.moonzhou.web;

import lombok.extern.slf4j.Slf4j;
import org.moonzhou.dto.TestDto;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 测试RequestBody方式获取参数
 * <p>
 * 1. 发起请求需要添加header，<code>Content-Type=application/json</code>
 *
 * @author moon-zhou <ayimin1989@163.com>
 * @version V1.0.0
 * @description
 * @date 2020/6/14 10:40
 * @since 1.0
 */
@Slf4j
@RestController
@RequestMapping("/testRequestBodyParam")
public class TestRequestBodyController {
    /**
     * http://localhost/testRequestBodyParam/index
     *
     * @return
     */
    @RequestMapping("/index")
    String index() {
        return "Hello World!";
    }

    /**
     * http://localhost/testRequestBodyParam/testDtoParam
     * <p>
     * 测试时，发起请求需要添加header，<code>Content-Type=application/json</code>，也就限制了上传的参数大对象只有一个
     *
     * @param testDto
     * @return
     */
    @RequestMapping("testDtoParam")
    public TestDto testDtoParam(@RequestBody TestDto testDto) {

        return testDto;
    }

    /**
     * http://localhost/testRequestBodyParam/testMapParam
     * <p>
     * 测试时，发起请求需要添加header，<code>Content-Type=application/json</code>，也就限制了上传的参数大对象只有一个
     *
     * @param params
     * @return
     */
    @RequestMapping("testMapParam")
    public Map<String, Object> testMapParam(@RequestBody Map<String, Object> params) {

        return params;
    }
}
