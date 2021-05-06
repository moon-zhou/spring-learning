package org.moonzhou.offline.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author moon-zhou <ayimin1989@163.com>
 * @version V1.0.0
 * @description
 * @date 2021/4/29 15:15
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
}
