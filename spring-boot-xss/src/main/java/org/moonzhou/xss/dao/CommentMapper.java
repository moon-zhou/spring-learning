/*
 * Copyright (C), 2002-2021, moon-zhou
 * FileName: MonitorDataMapper.java
 * Author:   moon-zhou
 * Email:    ayimin1989@163.com
 * Date:     2021/3/20 15:41
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名    修改时间    版本号       描述
 */
package org.moonzhou.xss.dao;

import org.moonzhou.xss.dmo.CommentDmo;
import org.moonzhou.xss.dmo.MonitorDataDmo;

import java.util.List;

/**
 * 功能描述: 数据库表comment对应的mybatis操作接口类<br>
 *
 * @author moon-zhou
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public interface CommentMapper {

    /**
     * 查询留言数据
     * @return
     */
    List<CommentDmo> findAll();

    /**
     * 插入留言数据
     * @return
     */
    int insertComment(CommentDmo commentDmo);
}
