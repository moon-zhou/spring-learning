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
package org.moonzhou.spring.ioc.scope.configuration;

import org.moonzhou.spring.ioc.condition.injection.demo001os.ShowCmd;
import org.moonzhou.spring.ioc.condition.injection.demo001os.condition.LinuxCondition;
import org.moonzhou.spring.ioc.condition.injection.demo001os.condition.WindowsCondition;
import org.moonzhou.spring.ioc.condition.injection.demo001os.impl.LinuxShowCmd;
import org.moonzhou.spring.ioc.condition.injection.demo001os.impl.WinShowCmd;
import org.moonzhou.spring.ioc.condition.injection.demo002datasource.DataSource;
import org.springframework.context.annotation.*;

/**
 * 功能描述: 代码方式配置bean<br>
 *
 * @author moon-zhou
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@Configuration
public class JavaConfig {

    @Bean
    @Scope("prototype")
    Drink drink() {
        return new Drink();
    }

    /**
     * 未添加scope，默认的是singleton
     * @return
     */
    @Bean
    Eat eat() {
        return new Eat();
    }

    /**
     * 配置方式，直接在bean的类上添加<code>@Scope("prototype")</code>，不生效
     * @return
     */
    @Bean
    Run run() {
        return new Run();
    }
}
