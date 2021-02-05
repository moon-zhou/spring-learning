package org.moonzhou.spring.ioc.bean;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.*;

public class UserComplexTest {

    @Test
    public void test() {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserComplex userComplex = (UserComplex) ctx.getBean("userComplex");
        System.out.println(userComplex);
    }

    @Test
    public void test2() {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserComplex2 userComplex2 = (UserComplex2) ctx.getBean("userComplex2");
        System.out.println(userComplex2);
    }

    @Test
    public void test3() {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserComplex3 userComplex3 = (UserComplex3) ctx.getBean("userComplex3");
        System.out.println(userComplex3);
    }

    @Test
    public void test4() {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserComplex4 userComplex4 = (UserComplex4) ctx.getBean("userComplex4");
        System.out.println(userComplex4);
    }

    @Test
    public void test5() {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserComplex5 userComplex5 = (UserComplex5) ctx.getBean("userComplex5");
        System.out.println(userComplex5);
    }
}