package org.moonzhou.spring.ioc.bean;

import org.junit.Test;
import org.moonzhou.spring.ioc.xml.bean.Device;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DeviceTest {

    @Test
    public void test() {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        Device device = (Device)ctx.getBean("device");
        System.out.println(device.toString());
    }
}