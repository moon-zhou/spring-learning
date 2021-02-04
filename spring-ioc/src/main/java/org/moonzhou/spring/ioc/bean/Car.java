/*
 * Copyright (C), 2002-2021, moon-zhou
 * FileName: Car.java
 * Author:   moon-zhou
 * Email:    ayimin1989@163.com
 * Date:     2021/2/4 17:22
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名    修改时间    版本号       描述
 */
package org.moonzhou.spring.ioc.bean;

import java.math.BigDecimal;

/**
 * 功能描述: 有参构造函数<br>
 *
 * @author moon-zhou
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class Car {
    private Integer id;

    private String name;

    private BigDecimal price;

    public Car() {
    }

    public Car(Integer id) {
        this.id = id;
    }

    public Car(String name) {
        this.name = name;
    }

    public Car(Integer id, String name, BigDecimal price) {
        System.out.println("------------init all field-------------");

        this.id = id;
        this.name = name;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
