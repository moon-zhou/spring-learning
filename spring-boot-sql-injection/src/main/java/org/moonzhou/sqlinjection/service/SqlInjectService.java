/*
 * Copyright (C), 2002-2021, moon-zhou
 * FileName: SqlInjectService.java
 * Author:   moon-zhou
 * Email:    ayimin1989@163.com
 * Date:     2021/2/2 16:51
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名    修改时间    版本号       描述
 */
package org.moonzhou.sqlinjection.service;

import org.moonzhou.sqlinjection.model.User;

import java.util.List;

/**
 * 功能描述:<br>
 *
 * @author moon-zhou
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public interface SqlInjectService {

    List<User> queryUserList(String id);
}
