/*
 * Copyright (C), 2002-2021, moon-zhou
 * FileName: FitnessServiceImpl.java
 * Author:   moon-zhou
 * Email:    ayimin1989@163.com
 * Date:     2021/4/18 10:26
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名    修改时间    版本号       描述
 */
package org.moonzhou.spring.aop.xmlconfig.biz;

/**
 * 功能描述:<br>
 *
 * @author moon-zhou
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class JobImpl {

    /**
     *
     * @param unitPrice  单价
     * @param deals  成交数
     * @return
     */
    public double earn(double unitPrice, int deals) {
        return unitPrice * deals;
    }

    public void penalize(double money, int times) {
        System.out.println(money * times);
    }
}
