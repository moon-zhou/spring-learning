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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 功能描述: 控制台输出日志打印测试类<br>
 *
 * @author moon-zhou
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@RestController
@RequestMapping("consoleLogTest")
public class SpringBootLogBackConsoleLogTestController {

    /**
     * 测试控制台日志格式-黑白：http://localhost/consoleLogTest/consoleNormal
     * 需要注释application.yml里相关的配置
     * @return
     */
    @RequestMapping("consoleNormal")
    String consoleNormal() {

        Logger log = LoggerFactory.getLogger("console_normal");

        log.trace("this is trace log...");
        log.debug("this is debug log...");
        log.info("this is info log...");
        log.warn("this is warn log...");
        log.error("this is error log...");

        return "console normal log";
    }

    /**
     * 测试控制台日志格式-黑白：http://localhost/consoleLogTest/consoleColor
     * 需要注释application.yml里相关的配置
     * @return
     */
    @RequestMapping("consoleColor")
    String consoleColor() {
        Logger log = LoggerFactory.getLogger("console_color");

        log.trace("this is trace log...");
        log.debug("this is debug log...");
        log.info("this is info log...");
        log.warn("this is warn log...");
        log.error("this is error log...");

        return "console color log";
    }
}
