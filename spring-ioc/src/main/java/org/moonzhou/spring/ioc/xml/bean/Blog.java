/*
 * Copyright (C), 2002-2021, moon-zhou
 * FileName: Blog.java
 * Author:   moon-zhou
 * Email:    ayimin1989@163.com
 * Date:     2021/2/4 17:52
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名    修改时间    版本号       描述
 */
package org.moonzhou.spring.ioc.xml.bean;

import java.math.BigDecimal;

/**
 * 功能描述: set方法初始化数据 - property<br>
 *
 * @author moon-zhou
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class Blog {
    private Integer id;

    private String name;

    private BigDecimal size;

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSize(BigDecimal size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "Blog{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", size=" + size +
                '}';
    }
}
