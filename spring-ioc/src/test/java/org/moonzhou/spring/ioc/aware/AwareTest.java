package org.moonzhou.spring.ioc.aware;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.*;

public class AwareTest {

    @Test
    public void test() {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");

        DriveService driveService = ctx.getBean(DriveService.class);
        String carExistInfo = driveService.existTransportation("car");

        System.out.println(carExistInfo);
        assertTrue(carExistInfo.contains("true"));

        String bikeExistInfo = driveService.existTransportation("bike");
        System.out.println(bikeExistInfo);
        assertTrue(bikeExistInfo.contains("false"));
    }

}