package org.moonzhou.profile.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author moon-zhou <ayimin1989@163.com>
 * @version V1.0.0
 * @description
 * @date 2021/6/10 15:15
 * @since 1.0
 */
@RestController
@RequestMapping("/test")
public class TestController {


    /**
     * http://localhost:8083/test/index
     *
     * @return
     */
    @RequestMapping("/index")
    String index() {
        return "hello offline project!!!";
    }

    /**
     * 测试返回json数据
     * http://localhost:8083/test/retJson
     * @return
     */
    @RequestMapping("retJson")
    @ResponseBody
    Map<String, Object> retJson() {
        Map<String, Object> result = new HashMap<>();
        result.put("success", true);

        return result;
    }
}
