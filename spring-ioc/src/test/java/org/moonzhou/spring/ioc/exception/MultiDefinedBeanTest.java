package org.moonzhou.spring.ioc.exception;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MultiDefinedBeanTest {

    @Test
    public void test() {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        MultiDefinedBean multiDefinedBean = (MultiDefinedBean) ctx.getBean("multiDefinedBean");
        System.out.println(multiDefinedBean.toString());

        MultiDefinedBean multiDefinedBeanOther = (MultiDefinedBean) ctx.getBean("multiDefinedBeanOther");
        System.out.println(multiDefinedBeanOther.toString());
    }
}