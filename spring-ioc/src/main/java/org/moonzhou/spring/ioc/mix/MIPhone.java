/*
 * Copyright (C), 2002-2021, moon-zhou
 * FileName: Phone.java
 * Author:   moon-zhou
 * Email:    ayimin1989@163.com
 * Date:     2021/2/24 19:44
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名    修改时间    版本号       描述
 */
package org.moonzhou.spring.ioc.mix;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * 功能描述:<br>
 *
 * @author moon-zhou
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@Component
public class MIPhone {

    private String brand;

    private BigDecimal price;

    private Long createTime = System.currentTimeMillis();

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Long getCreateTime() {
        return createTime;
    }
}
