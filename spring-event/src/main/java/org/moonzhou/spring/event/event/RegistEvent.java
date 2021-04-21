/*
 * Copyright (C), 2002-2021, moon-zhou
 * FileName: RegistEvent.java
 * Author:   moon-zhou
 * Email:    ayimin1989@163.com
 * Date:     2021/4/21 11:46
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名    修改时间    版本号       描述
 */
package org.moonzhou.spring.event.event;

import org.springframework.context.ApplicationEvent;

/**
 * 功能描述: 模拟注册业务，成功注册后，抛事件，相关发券，发短信监听后进行后置业务处理<br>
 *
 * @author moon-zhou
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class RegistEvent extends ApplicationEvent {

    private String userName;

    /**
     * Create a new {@code ApplicationEvent}.
     *
     * @param source the object on which the event initially occurred or with
     *               which the event is associated (never {@code null})
     */
    public RegistEvent(Object source) {
        super(source);
    }

    public RegistEvent(Object source, String userName) {
        super(source);
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
