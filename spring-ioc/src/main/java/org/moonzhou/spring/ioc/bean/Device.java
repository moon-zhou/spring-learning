/*
 * Copyright (C), 2002-2021, moon-zhou
 * FileName: Device.java
 * Author:   moon-zhou
 * Email:    ayimin1989@163.com
 * Date:     2021/2/3 15:31
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名    修改时间    版本号       描述
 */
package org.moonzhou.spring.ioc.bean;

import java.math.BigDecimal;

/**
 * 功能描述: 设备<br>
 *
 * @author moon-zhou
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class Device {

    private Integer type;

    private BigDecimal weight;

    private String dimension;

    private String brand;

    public Device() {
        System.out.println("--------------init device---------------");
    }

    public Device(Integer type, BigDecimal weight, String dimension, String brand) {
        this.type = type;
        this.weight = weight;
        this.dimension = dimension;
        this.brand = brand;
    }

    @Override
    public String toString() {
        return "Device{" +
                "type=" + type +
                ", weight=" + weight +
                ", dimension='" + dimension + '\'' +
                ", brand='" + brand + '\'' +
                '}';
    }
}
