package org.moonzhou.sqlinjection.web;

import lombok.extern.slf4j.Slf4j;
import org.moonzhou.sqlinjection.model.User;
import org.moonzhou.sqlinjection.service.SqlInjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author moon-zhou <ayimin1989@163.com>
 * @version V1.0.0
 * @description
 * @date 2020/6/6 11:35
 * @since 1.0
 */
@Slf4j
@RestController
@RequestMapping("/sqlInject")
public class SqlInjectController {

    @Autowired
    private SqlInjectService sqlInjectErrorService;

    @Autowired
    private SqlInjectService sqlInjectDefensedService;

    /**
     * 注入查询全量数据：
     * http://localhost:8080/sqlInject/error/1 or 1=1
     *
     * 注入查询版本信息：
     * http://localhost:8080/sqlInject/error/1 union select 1,1,version()
     *
     * 注入查询库名：
     * http://localhost:8080/sqlInject/error/1 union select 1,1,database()
     *
     * 注入查询所有库：
     * http://localhost:8080/sqlInject/error/1 union select 1,1,database() union select 1,1, (SELECT GROUP_CONCAT(schema_name) FROM information_schema.schemata) schemaName
     * @return
     */
    @RequestMapping("/error/{id}")
    public List<User> error(@PathVariable(name = "id") String id) {
        return sqlInjectErrorService.queryUserList(id);
    }

    /**
     * http://localhost:8080/sqlInject/right/1 or 1=1
     * @param id
     * @return
     */
    @RequestMapping("/right/{id}")
    public List<User> right(@PathVariable(name = "id") String id) {
        return sqlInjectDefensedService.queryUserList(id);
    }
}
