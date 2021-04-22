/*
 * Copyright (C), 2002-2021, moon-zhou
 * FileName: User.java
 * Author:   moon-zhou
 * Email:    ayimin1989@163.com
 * Date:     2021/4/22 14:28
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名    修改时间    版本号       描述
 */
package org.moonzhou.spring.event.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 功能描述:<br>
 *
 * @author moon-zhou
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {

    private String name;
}
