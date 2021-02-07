package org.moonzhou.spring.ioc.scan.xml;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class XmlConfigTest {

    @Test
    public void test() {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserXmlConfService userXmlConfService = ctx.getBean(UserXmlConfService.class);
        List<String> list = userXmlConfService.getAllUser();
        System.out.println(list);
    }

}