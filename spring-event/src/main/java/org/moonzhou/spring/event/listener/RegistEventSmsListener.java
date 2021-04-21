/*
 * Copyright (C), 2002-2021, moon-zhou
 * FileName: RegistEventSmsListener.java
 * Author:   moon-zhou
 * Email:    ayimin1989@163.com
 * Date:     2021/4/21 11:52
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名    修改时间    版本号       描述
 */
package org.moonzhou.spring.event.listener;

import org.moonzhou.spring.event.event.RegistEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * 功能描述:模拟注册发送短信监听<br>
 *
 * @author moon-zhou
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@Component
public class RegistEventSmsListener {

    @Order(1)
    @EventListener
    public void listen(RegistEvent registEvent) {
        System.out.println(registEvent.getUserName() + "已注册成功~~~");

        // 模拟耗时业务，同时也验证了多个事件监听，默认是单线程处理的。
        try {
            TimeUnit.SECONDS.sleep(10);
            System.out.println("done......");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 单线程处理中，如果一个时间监听出现异常，未进行catch处理，那么后续的监听都会被中断
        // throw new RuntimeException("interutp...");
    }
}
