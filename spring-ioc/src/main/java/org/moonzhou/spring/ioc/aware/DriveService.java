/*
 * Copyright (C), 2002-2021, moon-zhou
 * FileName: DriveService.java
 * Author:   moon-zhou
 * Email:    ayimin1989@163.com
 * Date:     2021/4/14 11:34
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名    修改时间    版本号       描述
 */
package org.moonzhou.spring.ioc.aware;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * 功能描述: spring aware 示例<br>
 *     此示例依赖：{@link org.moonzhou.spring.ioc.xml.bean.Car}，bean name为car
 * @author moon-zhou
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class DriveService implements ApplicationContextAware {

    /**
     * 定义私有变量，通过ApplicationContextAware接口set方法，将spring容器的ApplicationContext的对象信息，获取到当前bean里来进行使用。
     */
    private ApplicationContext applicationContext;

    public String existTransportation(String transportation) {
        //判断容器中是否存在某个 Bean
        boolean exist = applicationContext.containsBean(transportation);
        System.out.println(exist);
        return "transportation " + transportation + ": " + exist;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
