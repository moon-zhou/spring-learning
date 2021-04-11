/*
 * Copyright (C), 2002-2021, moon-zhou
 * FileName: SQLInjectionController.java
 * Author:   moon-zhou
 * Email:    ayimin1989@163.com
 * Date:     2021/4/11 15:35
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名    修改时间    版本号       描述
 */
package org.moonzhou.xss.web;

import org.moonzhou.xss.dto.SQLInjectionUser;
import org.moonzhou.xss.service.SQLInjectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 功能描述: 使用mybatis的情况下进行SQL注入示例及防御<br>
 *
 * @author moon-zhou
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@RestController
@RequestMapping("/sqlInjection")
public class SQLInjectionController {

    @Autowired
    private SQLInjectionService sqlInjectionService;

    /**
     * http://localhost:8080/sqlInjection/test
     * @return
     */
    @RequestMapping("/test")
    public List<SQLInjectionUser> getAllUser(@PathVariable(name = "id") String id) {
        return sqlInjectionService.getAllUser();
    }

    /**
     * http://localhost:8080/sqlInjection/testWrong
     *
     * 错误回显(mybatis时未回显到浏览器，但是服务器端控制台已显示)：
     * http://localhost:8080/sqlInjection/testWrong/1'
     *
     * 注入查询全量数据：
     * http://localhost:8080/sqlInjection/testWrong/1 or 1=1
     *
     * 注入查询版本信息：
     * http://localhost:8080/sqlInjection/testWrong/1 union select 1,1,version()
     *
     * 注入查询库名：
     * http://localhost:8080/sqlInjection/testWrong/1 union select 1,1,database()
     *
     * 注入查询所有库：
     * http://localhost:8080/sqlInjection/testWrong/1 union select 1,1,database() union select 1,1, (SELECT GROUP_CONCAT(schema_name) FROM information_schema.schemata) schemaName
     *
     *
     * @return
     */
    @RequestMapping("/testWrong/{name}")
    public List<SQLInjectionUser> getAllUserWrong(@PathVariable(name = "name") String name) {
        return sqlInjectionService.getAllUserWrong(initSearchCondition(name));
    }

    /**
         * http://localhost:8080/sqlInjection/testRight/moon1
     *
     * 错误回显(mybatis时未回显到浏览器，但是服务器端控制台已显示)：
     * http://localhost:8080/sqlInjection/testRight/1'
     *
     * 注入查询全量数据：
     * http://localhost:8080/sqlInjection/testRight/1 or 1=1
     *
     * 注入查询版本信息：
     * http://localhost:8080/sqlInjection/testRight/1 union select 1,1,version()
     *
     * 注入查询库名：
     * http://localhost:8080/sqlInjection/testRight/1 union select 1,1,database()
     *
     * 注入查询所有库：
     * http://localhost:8080/sqlInjection/testRight/1 union select 1,1,database() union select 1,1, (SELECT GROUP_CONCAT(schema_name) FROM information_schema.schemata) schemaName
     *
     *
     * @return
     */
    @RequestMapping("/testRight/{name}")
    public List<SQLInjectionUser> getAllUserRight(@PathVariable(name = "name") String name) {
        return sqlInjectionService.getAllUserRight(initSearchCondition(name));
    }

    /**
     * 构造查询条件
     * @param name
     * @return
     */
    private SQLInjectionUser initSearchCondition(String name) {

        return new SQLInjectionUser(name);
    }
}
