/*
 * Copyright (C), 2002-2021, moon-zhou
 * FileName: MyService.java
 * Author:   moon-zhou
 * Email:    ayimin1989@163.com
 * Date:     2021/4/20 17:38
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名    修改时间    版本号       描述
 */
package org.moonzhou.spring.event.publisher;

import org.moonzhou.spring.event.event.MyEvent;
import org.moonzhou.spring.event.event.RegistEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Service;

/**
 * 功能描述: 模拟注册后，通过ApplicationEventPublisher进行事件发布<br>
 *
 * @author moon-zhou
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@Service
public class RegistService implements ApplicationEventPublisherAware {

    private ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public void regist() {
        System.out.println("doing regist biz......");

        // 发布自定义事件
        RegistEvent registEvent = new RegistEvent("regist", "moon");
        applicationEventPublisher.publishEvent(registEvent);
    }
}
