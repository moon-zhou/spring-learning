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

import org.moonzhou.spring.event.event.LoginEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * 功能描述: 模拟登录后，通过ApplicationEventPublisher进行事件发布<br>
 *
 * @author moon-zhou
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@Service
public class LoginService implements ApplicationEventPublisherAware {

    private ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public void login() {
        System.out.println(this.getClass().getName() + "    " + Thread.currentThread().getName() + "    " + "doing login biz......");

        // 发布自定义事件
        LoginEvent loginEvent = new LoginEvent("regist", "moon");
        applicationEventPublisher.publishEvent(loginEvent);

        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(this.getClass().getName() + "    " + Thread.currentThread().getName() + "    " + "doing login end......");
    }
}
