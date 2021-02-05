package org.moonzhou.spring.ioc.bean;


import org.junit.Test;
import org.moonzhou.spring.ioc.xml.bean.Book;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BookTest {

    @Test
    public void test() {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        Book book = (Book) ctx.getBean("book");
        book.MySelfAddress();
    }

    /**
     * 此方式如果同一个class配置了多个bean，则会抛出异常
     * <code><bean id="book" class="org.moonzhou.spring.ioc.xml.bean.Book"/></code>
     * <code><bean id="book2" class="org.moonzhou.spring.ioc.xml.bean.Book"/></code>
     *
     * 异常：
     * org.springframework.beans.factory.NoUniqueBeanDefinitionException:
     * No qualifying bean of type 'org.moonzhou.spring.ioc.xml.bean.Book' available: expected single matching bean but found 2: book,book2
     *
     * 所以，一般建议使用 name 或者 id 去获取 Bean 的实例
     */
    @Test
    public void testGetBean() {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        Book book = ctx.getBean(Book.class);
        book.MySelfAddress();
    }
}