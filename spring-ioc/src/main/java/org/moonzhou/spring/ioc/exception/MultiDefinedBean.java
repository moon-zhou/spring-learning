/*
 * Copyright (C), 2002-2021, moon-zhou
 * FileName: MultiDefinedBean.java
 * Author:   moon-zhou
 * Email:    ayimin1989@163.com
 * Date:     2021/7/14 14:56
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名    修改时间    版本号       描述
 */
package org.moonzhou.spring.ioc.exception;

import org.springframework.stereotype.Component;

/**
 * 功能描述: 多种bean配置方式扫描<br>
 *     同时配置了注解和xml，没有出现实例化报错情况，因为是单例，所以根据beanId分别获取时，都获取到同一实例对象
 *
 * @author moon-zhou
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@Component
public class MultiDefinedBean {

    private Long createTime = System.currentTimeMillis();

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "MultiDefinedBean{" +
                "createTime=" + createTime +
                '}';
    }
}
