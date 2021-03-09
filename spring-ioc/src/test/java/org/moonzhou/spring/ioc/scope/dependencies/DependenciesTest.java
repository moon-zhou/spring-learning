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

    /**
     * 验证Performer本身就是多例的
     */
    @Test
    public void test02() {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        Performer performer1 = ctx.getBean(Performer.class);
        Performer performer2 = ctx.getBean(Performer.class);

        System.out.println(performer1);
        System.out.println(performer2);

        assertNotEquals(performer1.getName(), performer2.getName());
    }

    /**
     * 测试lookup方法
     */
    @Test
    public void test03() {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");

        HiMomLookup hiMomLookup1 = ctx.getBean(HiMomLookup.class);
        HiMomLookup hiMomLookup2 = ctx.getBean(HiMomLookup.class);

        System.out.println(hiMomLookup1.getPerformer());
        System.out.println(hiMomLookup2.getPerformer());

        assertNotEquals(hiMomLookup1.getPerformer().getName(), hiMomLookup2.getPerformer().getName());
    }

    /**
     * 测试getBean方法
     */
    @Test
    public void test04() {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");

        HiMomGetBean hiMomGetBean1 = ctx.getBean(HiMomGetBean.class);
        HiMomGetBean hiMomGetBean2 = ctx.getBean(HiMomGetBean.class);

        System.out.println("-----------直接get---------");
        System.out.println(hiMomGetBean1.getPerformer());
        System.out.println(hiMomGetBean2.getPerformer());

        System.out.println("-----------重新获取后get---------");
        hiMomGetBean1.createPerformer();
        Performer hiMomGetBean1Performer = hiMomGetBean1.getPerformer();
        System.out.println(hiMomGetBean1Performer);

        hiMomGetBean2.createPerformer();
        Performer hiMomGetBean2Performer = hiMomGetBean2.getPerformer();
        System.out.println(hiMomGetBean2Performer);

        assertNotEquals(hiMomGetBean1Performer.getName(), hiMomGetBean2Performer.getName());
    }

}