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
 * 功能描述:<br>
 *
 * @author moon-zhou
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@Slf4j
@RestController
@RequestMapping("springBootLogBackMDCTest")
public class SpringBootLogBackMDCTestController {

    /**
     * log in file with mdc
     * http://localhost/springBootLogBackMDCTest/logFileMdc
     * @return
     */
    @RequestMapping("logFileMdc")
    public String logFileMdc() {

        Logger log = LoggerFactory.getLogger("mdc");

        log.trace("this is trace log...");
        log.debug("this is debug log...");
        log.info("this is info log...");
        log.warn("this is warn log...");
        log.error("this is error log...");

        return "success";
    }
}
