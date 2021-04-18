package org.moonzhou.spring.aop;

import org.junit.Test;
import org.moonzhou.spring.aop.annotationconfig.JavaConfig;
import org.moonzhou.spring.aop.annotationconfig.biz.MyCalculatorImpl;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringAOPTest {

    /**
     * 测试自定义注解aop示例
     */
    @Test
    public void test() {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(JavaConfig.class);
        MyCalculatorImpl myCalculator = ctx.getBean(MyCalculatorImpl.class);
        myCalculator.add(3, 4);
        myCalculator.min(3, 4);
    }
}