/*
 * Copyright (C), 2002-2021, moon-zhou
 * FileName: RegistCouponEvent.java
 * Author:   moon-zhou
 * Email:    ayimin1989@163.com
 * Date:     2021/4/21 11:49
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

/**
 * 功能描述:模拟注册发券监听<br>
 *
 * @author moon-zhou
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@Component
public class RegistEventCouponListener {

    @Order(2)
    @EventListener
    public void listen(RegistEvent registEvent) {
        System.out.println("账号：" + registEvent.getUserName() + "注册完成。发送一张50元霸王券~~~");
    }
}
