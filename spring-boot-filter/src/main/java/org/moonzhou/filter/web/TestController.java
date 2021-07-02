package org.moonzhou.filter.web;

import org.moonzhou.filter.dto.CommonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author moon-zhou <ayimin1989@163.com>
 * @version V1.0.0
 * @description
 * @date 2021/5/30 09:45
 * @since 1.0
 */
@RestController
@RequestMapping("/test")
public class TestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestController.class);

    /**
     * http://localhost:8081/test/index
     *
     * @return
     */
    @RequestMapping("/index")
    String index() {
        LOGGER.info("filter test controller, index.");

        return "hello filter project!!!";
    }

    /**
     * 测试返回json数据
     * http://localhost:8081/test/json
     * @return
     */
    @RequestMapping("json")
    @ResponseBody
    CommonResult retJsonOff() {
        CommonResult result = new CommonResult();

        return result;
    }

    /**
     * http://localhost:8081/test/log
     * 日志级别从小到大为 trace < debug < info < warn < error < fatal，由于默认日志级别设置为 INFO，如果不修改配置，下面 trace 和 debug 级别的日志都看不到。
     *
     * Logback does not have a FATAL level. It is mapped to ERROR.
     */
    @RequestMapping("/log")
    public void test(){
        LOGGER.trace("Trace 日志...");
        LOGGER.debug("Debug 日志...");
        LOGGER.info("Info 日志...");
        LOGGER.warn("Warn 日志...");
        LOGGER.error("Error 日志...");
    }
}
