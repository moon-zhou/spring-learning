package org.moonzhou.doc.swagger.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author moon-zhou <ayimin1989@163.com>
 * @version V1.0.0
 * @description
 * @date 2022/2/19 14:03
 * @since 1.0
 */
@Api(tags = "Hello控制器")
@RestController
public class HelloController {
    @ApiOperation("展示欢迎信息")
    @GetMapping("/hello")
    public String hello(@ApiParam("名字") String name) {
        return "hello, " + name;
    }
}
