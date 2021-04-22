/*
 * Copyright (C), 2002-2021, moon-zhou
 * FileName: MyGenericEvent.java
 * Author:   moon-zhou
 * Email:    ayimin1989@163.com
 * Date:     2021/4/22 14:22
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名    修改时间    版本号       描述
 */
package org.moonzhou.spring.event.event;

import org.springframework.context.ApplicationEvent;

/**
 * 功能描述:<br>
 *
 * @author moon-zhou
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class MyGenericEvent<T> extends ApplicationEvent {

    private T genericParameter;

    /**
     * Create a new {@code ApplicationEvent}.
     *
     * @param source the object on which the event initially occurred or with
     *               which the event is associated (never {@code null})
     */
    public MyGenericEvent(Object source) {
        super(source);
    }

    public MyGenericEvent(Object source, T genericParameter) {
        super(source);
        this.genericParameter = genericParameter;
    }

    public T getGenericParameter() {
        return genericParameter;
    }

    public void setGenericParameter(T genericParameter) {
        this.genericParameter = genericParameter;
    }
}
