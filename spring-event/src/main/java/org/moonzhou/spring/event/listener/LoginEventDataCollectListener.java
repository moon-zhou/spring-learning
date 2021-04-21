/*
 * Copyright (C), 2002-2021, moon-zhou
 * FileName: LoginEventDataCollectListener.java
 * Author:   moon-zhou
 * Email:    ayimin1989@163.com
 * Date:     2021/4/21 16:50
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名    修改时间    版本号       描述
 */
package org.moonzhou.spring.event.listener;

import org.moonzhou.spring.event.event.LoginEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * 功能描述: 模拟登录时抛送数据分析中心<br>
 *
 * @author moon-zhou
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@Component
public class LoginEventDataCollectListener {

    @EventListener
    @Async
    public void listen(LoginEvent loginEvent) {
        System.out.println(this.getClass().getName() + "    " + Thread.currentThread().getName() + "    " + loginEvent.getUserName() + "登录数据收集完成~~~");

        try {
            TimeUnit.SECONDS.sleep(2);
            System.out.println(this.getClass().getName() + "    " + Thread.currentThread().getName() + "    " + "data collect done......");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
