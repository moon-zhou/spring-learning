/*
 * Copyright (C), 2002-2021, moon-zhou
 * FileName: GracefulShutDownController.java
 * Author:   moon-zhou
 * Email:    ayimin1989@163.com
 * Date:     2021/6/16 10:26
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名    修改时间    版本号       描述
 */
package org.moonzhou.profile.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * 功能描述: 优雅关机示例<br>
 *
 * @author moon-zhou
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@Controller
@RequestMapping("gracefulShutDown")
public class GracefulShutDownController {

    /**
     * 优雅关机，模拟关机时，仍有业务执行的方法
     * http://localhost:8083/gracefulShutDown/shutdown
     * @return
     */
    @RequestMapping("shutdown")
    @ResponseBody
    Map<String, Object> shutdown() {
        Map<String, Object> result = new HashMap<>();
        result.put("success", true);
        result.put("data", UUID.randomUUID());

        System.out.println("biz doing......");

        try {
            TimeUnit.SECONDS.sleep(15);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("biz end......");

        return result;
    }
}
