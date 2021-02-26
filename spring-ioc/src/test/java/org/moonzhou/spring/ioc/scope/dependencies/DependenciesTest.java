package org.moonzhou.spring.ioc.scope.dependencies;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.*;

public class DependenciesTest {

    /**
     * singleton-scoped beans with dependencies on prototype beans
     * 那么单例实例里的多例也会变成单例
     *
     * When you use singleton-scoped beans with dependencies on prototype beans,
     * be aware that dependencies are resolved at instantiation time.
     * Thus, if you dependency-inject a prototype-scoped bean into a singleton-scoped bean,
     * a new prototype bean is instantiated and then dependency-injected into the singleton bean.
     * The prototype instance is the sole instance that is ever supplied to the singleton-scoped bean.
     *
     * <link>https://docs.spring.io/spring-framework/docs/5.3.4/reference/html/core.html#beans-factory-scopes-sing-prot-interaction</link>
     */
    @Test
    public void test01() {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        HiMom hiMom1 = ctx.getBean(HiMom.class);
        HiMom hiMom2 = ctx.getBean(HiMom.class);

        System.out.println(hiMom1);
        System.out.println(hiMom2);

        assertEquals(hiMom1.getPerformer().getName(), hiMom2.getPerformer().getName());

    }

    @Test
    public void test02() {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        Performer performer1 = ctx.getBean(Performer.class);
        Performer performer2 = ctx.getBean(Performer.class);

        System.out.println(performer1);
        System.out.println(performer2);

        assertNotEquals(performer1.getName(), performer2.getName());
    }

}