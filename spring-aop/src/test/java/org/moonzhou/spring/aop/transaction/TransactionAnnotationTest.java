package org.moonzhou.spring.aop.transaction;

import org.junit.Before;
import org.junit.Test;
import org.moonzhou.spring.aop.transaction.service.annotation.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

public class TransactionAnnotationTest {

    private JdbcTemplate jdbcTemplate;

    private UserService userService;

    @Before
    public void before() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        jdbcTemplate = applicationContext.getBean(JdbcTemplate.class);
        userService = applicationContext.getBean(UserService.class, "annotationUserService");
    }

    /**
     * 测试xml配置事务管理
     */
    @Test
    public void test() {
        userService.updateMoney();
    }
}