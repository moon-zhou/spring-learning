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
package org.moonzhou.event.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.moonzhou.event.event.LogEvent;
import org.moonzhou.event.event.LogPublisher;
import org.moonzhou.event.param.LogParam;
import org.moonzhou.event.service.LogService;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * 功能描述:<br>
 *
 * @author moon-zhou
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/log/event")
public class LogEventController {

    private final ApplicationContext applicationContext;

    private final LogPublisher logPublisher;

    private final LogService logService;

    /**
     * 测试请求：http://localhost:8080/log/event/index
     *
     * @return
     */
    @RequestMapping("index")
    String index() throws InterruptedException {
        LogParam logParam = new LogParam();
        logParam.setRequestId(UUID.randomUUID().toString())
                .setParam("{\"test\":\"test\"}")
                .setOperateTime(LocalDateTime.now().toString());

        LogEvent logEvent = new LogEvent(logParam);

        applicationContext.publishEvent(logEvent);

        TimeUnit.SECONDS.sleep(5);

        log.info("-------------------------");

        logPublisher.sendEvent(logParam);

        return "success!";
    }


    /**
     * 测试请求：http://localhost:8080/log/event/async
     *
     * @return
     */
    @RequestMapping("async")
    String async() {
        LogParam logParam = new LogParam();
        logParam.setRequestId(UUID.randomUUID().toString())
                .setParam("{\"test\":\"test\"}")
                .setOperateTime(LocalDateTime.now().toString());

        logService.async(logParam);

        return "success!";
    }


}
