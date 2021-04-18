package org.moonzhou.spring.aop.jdbctemplate;

import org.junit.Before;
import org.junit.Test;
import org.moonzhou.spring.aop.jdbctemplate.annotationconfig.JdbcConfig;
import org.moonzhou.spring.aop.jdbctemplate.dmo.User;
import org.moonzhou.spring.aop.jdbctemplate.dmo.UserAlias;
import org.moonzhou.spring.aop.jdbctemplate.dmo.UserRowMapper;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.*;

public class JdbcTemplateAnnotationTest {

    private JdbcTemplate jdbcTemplate;

    @Before
    public void before() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(JdbcConfig.class);
        jdbcTemplate = applicationContext.getBean(JdbcTemplate.class);
    }

    /**
     * jdbc template 测试
     * 注解初始化方式
     */
    @Test
    public void insert() {
        jdbcTemplate.update("insert into aop_user (username,address) values (?,?);", "moon", "moon-zhou.github.io");
    }

    @Test
    public void update() {
        jdbcTemplate.update("update aop_user set username=? where id=?", "moon1", 1);
    }

    @Test
    public void delete() {
        jdbcTemplate.update("delete from aop_user where id=?", 2);
    }

    @Test
    public void select() {
        User user = jdbcTemplate.queryForObject("select * from aop_user where id=?", new BeanPropertyRowMapper<User>(User.class), 1);
        assertNotNull(user);
        System.out.println(user);
    }

    @Test
    public void select2() {
        UserAlias user = jdbcTemplate.queryForObject("select id,username as name,address from aop_user where id=?", new BeanPropertyRowMapper<UserAlias>(UserAlias.class), 1);
        assertNotNull(user);
        System.out.println(user);
    }

    @Test
    public void select3() {
        UserRowMapper user = jdbcTemplate.queryForObject("select * from aop_user where id=?", new RowMapper<UserRowMapper>() {
            public UserRowMapper mapRow(ResultSet resultSet, int i) throws SQLException {
                int id = resultSet.getInt("id");
                String username = resultSet.getString("username");
                String address = resultSet.getString("address");
                UserRowMapper u = new UserRowMapper();
                u.setId(id);
                u.setName(username);
                u.setAddress(address);
                return u;
            }
        }, 1);
        assertNotNull(user);
        System.out.println(user);
    }
}