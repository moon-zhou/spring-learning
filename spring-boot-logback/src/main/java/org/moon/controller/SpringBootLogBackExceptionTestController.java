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
import org.moon.annotation.MoonExceptionLog;
import org.moon.base.Result;
import org.moon.exception.MoonBizRunTimeException;
import org.springframework.web.bind.annotation.PathVariable;
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
@RequestMapping("springBootLogBackExceptionTest")
public class SpringBootLogBackExceptionTestController {

    /**
     * 测试出入参统一打印：
     * http://localhost/springBootLogBackExceptionTest/logException/1
     * http://localhost/springBootLogBackExceptionTest/logException/2
     *
     * @return
     */
    @MoonExceptionLog(logFor = {MoonBizRunTimeException.class})
    @RequestMapping("logException/{status}")
    public Result test(@PathVariable String status) {

        log.trace("this is trace log...");
        log.debug("this is debug log...");
        log.info("this is info log...");
        log.warn("this is warn log...");
        log.error("this is error log...");

        switch (status) {
            case "1":
                throw new RuntimeException("test");
            case "2":
                throw new MoonBizRunTimeException("moon");
            default:
                log.info("default: {}", status);
        }

        return Result.success();
    }

}
