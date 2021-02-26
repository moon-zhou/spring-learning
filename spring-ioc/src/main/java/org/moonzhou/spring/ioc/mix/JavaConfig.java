/*
 * Copyright (C), 2002-2021, moon-zhou
 * FileName: JavaConfig.java
 * Author:   moon-zhou
 * Email:    ayimin1989@163.com
 * Date:     2021/2/5 15:24
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名    修改时间    版本号       描述
 */
package org.moonzhou.spring.ioc.mix;

import org.moonzhou.spring.ioc.configuration.bean.SayHello;
import org.moonzhou.spring.ioc.configuration.bean.SayHi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * 功能描述: 混合模式<br>
 *     Java配置 + XML配置
 *
 * @author moon-zhou
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@Configuration
@ImportResource("classpath:applicationContext.xml")
public class JavaConfig {

    @Bean
    MIPhone miPhone() {
        return new MIPhone();
    }
}
