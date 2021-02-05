package org.moonzhou.spring.ioc.configuration;

import org.junit.Test;
import org.moonzhou.spring.ioc.configuration.bean.SayHello;
import org.moonzhou.spring.ioc.configuration.bean.SayHi;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.Assert.*;

public class JavaConfigTest {

    @Test
    public void test() {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(JavaConfig.class);
        SayHello hello = ctx.getBean(SayHello.class);
        System.out.println(hello.sayHello("moon"));
    }

    @Test
    public void test2() {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(JavaConfig.class);
        SayHi hi = (SayHi) ctx.getBean("sayHi");
        System.out.println(hi.sayHello("moon"));

        System.out.println("=====================================");

        SayHi hi2 = (SayHi) ctx.getBean("sayHi2");
        System.out.println(hi2.sayHello("moon2"));

        System.out.println("=====================================");

        SayHi goodHi = (SayHi) ctx.getBean("goodHi");
        System.out.println(goodHi.sayHello("good moon"));
    }
}