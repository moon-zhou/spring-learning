package org.moonzhou.spring.ioc.bean;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CarTest {

    @Test
    public void test() {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        Car car = (Car)ctx.getBean("car");
        System.out.println(car.toString());
    }
}