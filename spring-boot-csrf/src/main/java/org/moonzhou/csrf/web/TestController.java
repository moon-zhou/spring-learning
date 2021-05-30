package org.moonzhou.csrf.web;

import org.moonzhou.csrf.dto.CommonResult;
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


    /**
     * http://localhost:8080/test/index
     *
     * @return
     */
    @RequestMapping("/index")
    String index() {
        return "hello offline project!!!";
    }

    /**
     * 测试返回json数据
     * http://localhost:8080/test/json
     * @return
     */
    @RequestMapping("json")
    @ResponseBody
    CommonResult retJsonOff() {
        CommonResult result = new CommonResult();

        return result;
    }
}
