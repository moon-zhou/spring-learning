package org.moonzhou.spring.aop.transaction;

import org.junit.Before;
import org.junit.Test;
import org.moonzhou.spring.aop.jdbctemplate.dmo.User;
import org.moonzhou.spring.aop.jdbctemplate.dmo.UserAlias;
import org.moonzhou.spring.aop.jdbctemplate.dmo.UserRowMapper;
import org.moonzhou.spring.aop.transaction.service.xml.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.assertNotNull;

public class TransactionXmlTest {

    private JdbcTemplate jdbcTemplate;

    private UserService userService;

    @Before
    public void before() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        jdbcTemplate = applicationContext.getBean(JdbcTemplate.class);
        userService = applicationContext.getBean(UserService.class, "xmlUserService");
    }

    /**
     * 测试xml配置事务管理
     */
    @Test
    public void test() {
        userService.updateMoney();
    }
}