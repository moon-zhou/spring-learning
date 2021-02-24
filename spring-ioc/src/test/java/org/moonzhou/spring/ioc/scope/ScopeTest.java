/*
 * Copyright (C), 2002-2021, moon-zhou
 * FileName: ScopeTest.java
 * Author:   moon-zhou
 * Email:    ayimin1989@163.com
 * Date:     2021/2/22 20:37
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名    修改时间    版本号       描述
 */
package org.moonzhou.spring.ioc.scope;

import org.junit.Assert;
import org.junit.Test;
import org.moonzhou.spring.ioc.scope.configuration.Drink;
import org.moonzhou.spring.ioc.scope.configuration.Eat;
import org.moonzhou.spring.ioc.scope.configuration.JavaConfig;
import org.moonzhou.spring.ioc.scope.configuration.Run;
import org.moonzhou.spring.ioc.scope.scan.KeyBoard;
import org.moonzhou.spring.ioc.scope.xml.Cup;
import org.moonzhou.spring.ioc.scope.xml.Tea;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 功能描述:<br>
 *
 * @author moon-zhou
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class ScopeTest {

    /**
     * 测试单例的bean对象
     */
    @Test
    public void test01() {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        Cup cup1 = ctx.getBean("cup", Cup.class);
        Cup cup2 = ctx.getBean("cup", Cup.class);

        System.out.println(cup1 == cup2);
    }

    @Test
    public void test02() {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");

        Tea tea1 = ctx.getBean("tea", Tea.class);
        Tea tea2 = ctx.getBean("tea", Tea.class);

        System.out.println(tea1 == tea2);
    }

    @Test
    public void test03() {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(JavaConfig.class);

        Drink drink1 = ctx.getBean(Drink.class);
        Drink drink2 = ctx.getBean(Drink.class);
        System.out.println(drink1 == drink2);

        // scope范围的bean，每一次获取的实例对象都不是一个
        Assert.assertNotEquals(drink1, drink2);

        Eat eat1 = ctx.getBean(Eat.class);
        Eat eat2 = ctx.getBean(Eat.class);
        System.out.println(eat1 == eat2);

        // 未添加scope，默认的是singleton，获取到的bean始终都是一个
        Assert.assertEquals(eat1, eat2);

    }

    @Test
    public void test04() {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(JavaConfig.class);

        Run run1 = ctx.getBean(Run.class);
        Run run2 = ctx.getBean(Run.class);

        System.out.println(run1 == run2);
        Assert.assertEquals(run1, run2);
    }

    /**
     * 包扫描方式初始化，<code>@Scope</code>直接加在bean的类上
     */
    @Test
    public void test05() {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");

        KeyBoard keyBoard1 = ctx.getBean("keyBoard", KeyBoard.class);
        KeyBoard keyBoard2 = ctx.getBean("keyBoard", KeyBoard.class);

        System.out.println(keyBoard1 == keyBoard2);
        Assert.assertNotEquals(keyBoard1, keyBoard2);
    }
}
