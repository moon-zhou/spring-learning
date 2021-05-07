package org.moonzhou.offline.web.execution;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 表达式离线http服务示例
 * @author moon-zhou <ayimin1989@163.com>
 * @version V1.0.0
 * @description
 * @date 2021/5/6 17:05
 * @since 1.0
 */
@RestController
@RequestMapping("/eoffline")
public class EOfflineController {


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
