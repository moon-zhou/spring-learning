package org.moonzhou.spring.ioc.mix;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.Assert.*;

public class JavaConfigTest {


    @Test
    public void test() {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(JavaConfig.class);

        MIPhone miPhone = ctx.getBean(MIPhone.class);
        IPhone iPhone = ctx.getBean(IPhone.class);

        System.out.println(miPhone.getCreateTime());
        System.out.println(iPhone.getCreateTime());

        Assert.assertNotNull(miPhone);
        Assert.assertNotNull(iPhone);
    }
}