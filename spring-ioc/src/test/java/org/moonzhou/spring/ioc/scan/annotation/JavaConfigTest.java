package org.moonzhou.spring.ioc.scan.annotation;

import org.junit.Test;
import org.moonzhou.spring.ioc.scan.annotation.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.Assert.*;

public class JavaConfigTest {

    @Test
    public void test() {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(JavaConfig.class);
        UserService userService = ctx.getBean(UserService.class);
        System.out.println(userService.getAllUser());
    }
}