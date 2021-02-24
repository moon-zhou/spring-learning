/*
 * Copyright (C), 2002-2021, moon-zhou
 * FileName: Cup.java
 * Author:   moon-zhou
 * Email:    ayimin1989@163.com
 * Date:     2021/2/22 20:27
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名    修改时间    版本号       描述
 */
package org.moonzhou.spring.ioc.scope.xml;

import java.math.BigDecimal;

/**
 * 功能描述:<br>
 *
 * @author moon-zhou
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class Cup {

    private BigDecimal privice;

    private String brand;

    private String model;

    private Double weight;

    public BigDecimal getPrivice() {
        return privice;
    }

    public void setPrivice(BigDecimal privice) {
        this.privice = privice;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Cup{" +
                "privice=" + privice +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", weight=" + weight +
                '}';
    }
}
