package org.moonzhou.spring.ioc.bean;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BlogTest {

    @Test
    public void test() {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        Blog blog = (Blog) ctx.getBean("blog");
        System.out.println(blog.toString());
    }

    @Test
    public void test2() {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        Blog blog = (Blog) ctx.getBean("blog2");
        System.out.println(blog.toString());
    }
}