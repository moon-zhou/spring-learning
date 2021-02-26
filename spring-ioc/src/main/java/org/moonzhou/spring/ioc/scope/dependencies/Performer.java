/*
 * Copyright (C), 2002-2021, moon-zhou
 * FileName: Performer.java
 * Author:   moon-zhou
 * Email:    ayimin1989@163.com
 * Date:     2021/2/26 17:44
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名    修改时间    版本号       描述
 */
package org.moonzhou.spring.ioc.scope.dependencies;

import java.util.UUID;

/**
 * 功能描述:<br>
 *
 * @author moon-zhou
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class Performer {

    private String name = UUID.randomUUID().toString();

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Performer{" +
                "name='" + name + '\'' +
                '}';
    }
}
