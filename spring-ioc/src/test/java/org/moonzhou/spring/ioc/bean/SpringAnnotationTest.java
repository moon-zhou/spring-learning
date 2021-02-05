/*
 * Copyright (C), 2002-2021, moon-zhou
 * FileName: SpringAnnotationTest.java
 * Author:   moon-zhou
 * Email:    ayimin1989@163.com
 * Date:     2021/2/2 20:17
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名    修改时间    版本号       描述
 */
package org.moonzhou.spring.ioc.bean;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.moonzhou.spring.ioc.xml.bean.Book;
import org.moonzhou.spring.ioc.xml.bean.Device;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * 功能描述:<br>
 *
 * @author moon-zhou
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:applicationContext.xml"})
public class SpringAnnotationTest {

    @Resource
    Book book;

    @Autowired
    Device device;

    @Test
    public void getBookTest(){
        book.MySelfAddress();
    }

    @Test
    public void getDeviceTest() {
        System.out.println(device);
    }
}
