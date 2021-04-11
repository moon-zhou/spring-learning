/*
 * Copyright (C), 2002-2021, moon-zhou
 * FileName: SQLInjectionMapper.java
 * Author:   moon-zhou
 * Email:    ayimin1989@163.com
 * Date:     2021/4/11 15:39
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名    修改时间    版本号       描述
 */
package org.moonzhou.xss.dao;

import org.moonzhou.xss.dmo.UserDmo;

import java.util.List;

/**
 * 功能描述:<br>
 *
 * @author moon-zhou
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public interface SQLInjectionMapper {

    /**
     * 测试mvc功能，保证可以查到数据，为了方便，查询所有数据
     * @return
     */
    List<UserDmo> findAll();

    /**
     * 带条件查询，但是会有SQL Injection
     * @param userDmo 查询条件
     * @return
     */
    List<UserDmo> findAllWrong(UserDmo userDmo);

    /**
     * 带条件查询，无SQL Injection
     * @param userDmo 查询条件
     * @return
     */
    List<UserDmo> findAllRight(UserDmo userDmo);
}
