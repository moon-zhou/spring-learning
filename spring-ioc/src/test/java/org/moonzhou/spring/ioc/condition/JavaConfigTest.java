package org.moonzhou.spring.ioc.condition;

import org.junit.Test;
import org.moonzhou.spring.ioc.condition.injection.JavaConfig;
import org.moonzhou.spring.ioc.condition.injection.demo001os.ShowCmd;
import org.moonzhou.spring.ioc.condition.injection.demo002datasource.DataSource;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class JavaConfigTest {

    @Test
    public void testOS() {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(JavaConfig.class);
        ShowCmd showCmd = (ShowCmd) ctx.getBean("showCmd");
        System.out.println(showCmd.showCmd());
    }

    @Test
    public void testDatasource() {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.getEnvironment().setActiveProfiles("dev");
        ctx.register(JavaConfig.class);
        ctx.refresh();

        DataSource ds = (DataSource) ctx.getBean("ds");
        System.out.println(ds);
    }
}