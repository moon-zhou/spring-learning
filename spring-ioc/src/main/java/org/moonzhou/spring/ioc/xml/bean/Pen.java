/*
 * Copyright (C), 2002-2021, moon-zhou
 * FileName: Pen.java
 * Author:   moon-zhou
 * Email:    ayimin1989@163.com
 * Date:     2021/2/24 17:53
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名    修改时间    版本号       描述
 */
package org.moonzhou.spring.ioc.xml.bean;

import java.math.BigDecimal;

/**
 * 功能描述:<br>
 *
 * 使用bean的name进行实例化
 * @author moon-zhou
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class Pen {

    private Long createTime = System.currentTimeMillis();

    private Double weight;

    private BigDecimal price;

    private String brand;

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Long getCreateTime() {
        return createTime;
    }
}
