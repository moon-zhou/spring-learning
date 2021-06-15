/*
 * Copyright (C), 2002-2021, moon-zhou
 * FileName: ConditionalServiceController.java
 * Author:   moon-zhou
 * Email:    ayimin1989@163.com
 * Date:     2021/6/15 16:43
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名    修改时间    版本号       描述
 */
package org.moonzhou.profile.web;

import org.moonzhou.profile.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * 功能描述: 根据环境选择不同的服务测试类<br>
 *
 * @author moon-zhou
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@Controller
@RequestMapping("/service")
public class ConditionalServiceController {

    @Autowired
    private HelloService helloService;

    /**
     * 配合spring.profiles.active进行测试
     * dev: http://localhost:8081/service/hello
     * prd: http://localhost:8083/service/hello
     * @return
     */
    @RequestMapping("hello")
    @ResponseBody
    Map<String, Object> hello() {
        Map<String, Object> result = new HashMap<>();
        result.put("success", true);
        result.put("data", helloService.hello());

        return result;
    }

}
