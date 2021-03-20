package org.moonzhou.xss.web;

import lombok.extern.slf4j.Slf4j;
import org.moonzhou.xss.dto.MonitorData;
import org.moonzhou.xss.service.MonitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 用于监听记录，xss攻击之后，将用户的cookie信息构造请求，发送给攻击者
 * 攻击者可以存储下来或者即时利用这些数据进行二次攻击
 *
 * @author moon-zhou <ayimin1989@163.com>
 * @version V1.0.0
 * @description
 * @date 2021/3/19 17:35
 * @since 1.0
 */
@Slf4j
@RestController
@RequestMapping("/listener")
public class ListenerController {

    @Autowired
    private MonitorService monitorService;

    /**
     * http://localhost:8080/listener/log
     *
     * 打印攻击获取到的数据
     * @return
     */
    @RequestMapping("/log")
    void log(@RequestBody MonitorData monitorData) {

        // 此处仅输出到控制台
        log.info("listening...");
        log.info(monitorData.toString());
    }

    /**
     * http://localhost:8080/listener/store
     * 
     * 存储攻击的数据
     */
    @RequestMapping("/store")
    void store(MonitorData monitorData) {

    }

    /**
     * http://localhost:8080/listener/getAllMonitorData
     * 展示所有攻击获取到的数据
     * @return
     */
    @RequestMapping("/getAllMonitorData")
    List<MonitorData> getAllMonitorData() {

        return monitorService.getAllMonitorData();
    }
}
