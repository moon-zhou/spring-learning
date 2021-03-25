/*
 * Copyright (C), 2002-2021, moon-zhou
 * FileName: MonitorService.java
 * Author:   moon-zhou
 * Email:    ayimin1989@163.com
 * Date:     2021/3/20 15:55
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名    修改时间    版本号       描述
 */
package org.moonzhou.xss.service;

import org.moonzhou.xss.dao.CommentMapper;
import org.moonzhou.xss.dao.MonitorDataMapper;
import org.moonzhou.xss.dmo.CommentDmo;
import org.moonzhou.xss.dmo.MonitorDataDmo;
import org.moonzhou.xss.dto.Comment;
import org.moonzhou.xss.dto.MonitorData;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 功能描述:<br>
 *
 * @author moon-zhou
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@Service
public class CommentService {

    @Resource
    private CommentMapper commentMapper;

    public List<Comment> getAllComment() {

        List<Comment> comments = new ArrayList<>();
        List<CommentDmo> monitorDataDmos = commentMapper.findAll();

        for (CommentDmo commentDmo : monitorDataDmos) {
            Comment comment = new Comment();
            BeanUtils.copyProperties(commentDmo, comment);

            comments.add(comment);
        }

        return comments;
    }

    public int insertComment(Comment comment) {

        CommentDmo commentDmo = new CommentDmo();

        BeanUtils.copyProperties(comment, commentDmo);

        return commentMapper.insertComment(commentDmo);
    }
}
