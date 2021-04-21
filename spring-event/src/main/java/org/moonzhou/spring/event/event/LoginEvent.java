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
 * 功能描述: 模拟登录业务<br/>
 *     登录成功后事后风控，判断是否为风向账号，进行相关提示服务（短信，邮件等。）<br />
 *     登录成功后，登录相关信息抛送数据集市，做后续数据分析，智能登录推荐等的基础数据。<br/>
 *
 * @author moon-zhou
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class LoginEvent extends ApplicationEvent {

    private String userName;

    /**
     * Create a new {@code ApplicationEvent}.
     *
     * @param source the object on which the event initially occurred or with
     *               which the event is associated (never {@code null})
     */
    public LoginEvent(Object source) {
        super(source);
    }

    public LoginEvent(Object source, String userName) {
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
