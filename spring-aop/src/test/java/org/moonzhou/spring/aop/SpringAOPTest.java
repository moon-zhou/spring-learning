package org.moonzhou.spring.aop;

import org.junit.Test;
import org.moonzhou.spring.aop.annotationconfig.JavaConfig;
import org.moonzhou.spring.aop.annotationconfig.biz.MyCalculatorImpl;
import org.moonzhou.spring.aop.xmlconfig.biz.JobImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringAOPTest {

    /**
     * 测试自定义注解aop示例
     */
    @Test
    public void testAnnotationAop() {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(JavaConfig.class);
        MyCalculatorImpl myCalculator = ctx.getBean(MyCalculatorImpl.class);
        myCalculator.add(3, 4);
        myCalculator.min(3, 4);
    }

    /**
     * 测试xml配置aop示例
     */
    @Test
    public void testXmlAop() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        JobImpl job = applicationContext.getBean(JobImpl.class);

        job.earn(9.99, 9);
        job.penalize(3.33, 1);
    }
}