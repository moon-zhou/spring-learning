/*
 * Copyright (C), 2002-2020, moon-zhou
 * FileName: TestController.java
 * Author:   moon-zhou
 * Email:    ayimin1989@163.com
 * Date:     2020/7/14 11:47
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名    修改时间    版本号       描述
 */
package org.moon.controller;

import lombok.extern.slf4j.Slf4j;
import org.moon.base.Result;
import org.moon.dto.UserDto;
import org.moon.param.UserParam;
import org.moon.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 功能描述:<br>
 *
 * @author moon-zhou
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@Slf4j
@RestController
@RequestMapping("springBootLogBackAspectTest")
public class SpringBootLogBackAspectTestController {

    final UserService userService;

    public SpringBootLogBackAspectTestController(UserService userService) {
        this.userService = userService;
    }

    /**
     * 测试出入参统一打印：http://localhost/springBootLogBackAspectTest/logAspectResult
     * @return
     */
    @RequestMapping("logAspectResult")
    public Result<UserDto> getUser(UserParam userParam) {

        log.trace("this is trace log...");
        log.debug("this is debug log...");
        log.info("this is info log...");
        log.warn("this is warn log...");
        log.error("this is error log...");

        return Result.success(userService.getUser(userParam));
    }

    /**
     * 测试出入参统一打印：http://localhost/springBootLogBackAspectTest/logAspectMap?username=moon
     * @return
     */
    @RequestMapping("logAspectMap")
    public Result<Map<String, Object>> getUserMap(String username) {

        log.trace("this is trace log...");
        log.debug("this is debug log...");
        log.info("this is info log...");
        log.warn("this is warn log...");
        log.error("this is error log...");

        Map<String, Object> user = new HashMap<>();
        user.put("name", username + "test");
        user.put("age", 18);

        return Result.success(user);
    }

}
