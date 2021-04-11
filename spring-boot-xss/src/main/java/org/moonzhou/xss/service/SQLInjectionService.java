/*
 * Copyright (C), 2002-2021, moon-zhou
 * FileName: SQLInjection.java
 * Author:   moon-zhou
 * Email:    ayimin1989@163.com
 * Date:     2021/4/11 15:38
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名    修改时间    版本号       描述
 */
package org.moonzhou.xss.service;

import org.moonzhou.xss.dao.SQLInjectionMapper;
import org.moonzhou.xss.dmo.UserDmo;
import org.moonzhou.xss.dto.SQLInjectionUser;
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
public class SQLInjectionService {

    @Resource
    private SQLInjectionMapper sqlInjectionMapper;

    public List<SQLInjectionUser> getAllUser() {
        List<UserDmo> userDmoList = sqlInjectionMapper.findAll();

        return dmoConvert2dto(userDmoList);
    }

    public List<SQLInjectionUser> getAllUserWrong(SQLInjectionUser sqlInjectionUser) {

        List<UserDmo> userDmoList = sqlInjectionMapper.findAllWrong(dtoConvert2dmo(sqlInjectionUser));

        return dmoConvert2dto(userDmoList);
    }

    public List<SQLInjectionUser> getAllUserRight(SQLInjectionUser sqlInjectionUser) {
        List<UserDmo> userDmoList = sqlInjectionMapper.findAllRight(dtoConvert2dmo(sqlInjectionUser));

        return dmoConvert2dto(userDmoList);
    }

    /**
     * 查询数据时，查询条件从dto转换为dmo
     * @param sqlInjectionUser
     * @return
     */
    public UserDmo dtoConvert2dmo(SQLInjectionUser sqlInjectionUser) {
        UserDmo userDmo = new UserDmo();
        BeanUtils.copyProperties(sqlInjectionUser, userDmo);

        return userDmo;
    }

    /**
     * 数据库结果展示时需要从dmo转换为dto
     * @param userDmoList
     * @return
     */
    private List<SQLInjectionUser> dmoConvert2dto(List<UserDmo> userDmoList) {
        List<SQLInjectionUser> sqlInjectionUserList = new ArrayList<>();

        for (UserDmo userDmo : userDmoList) {
            SQLInjectionUser sqlInjectionUser = new SQLInjectionUser();
            BeanUtils.copyProperties(userDmo, sqlInjectionUser);

            sqlInjectionUserList.add(sqlInjectionUser);
        }
        return sqlInjectionUserList;
    }


}
