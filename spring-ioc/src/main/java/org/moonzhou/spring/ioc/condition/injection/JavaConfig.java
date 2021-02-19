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
package org.moonzhou.spring.ioc.condition.injection;

import org.moonzhou.spring.ioc.condition.injection.demo001os.ShowCmd;
import org.moonzhou.spring.ioc.condition.injection.demo001os.condition.LinuxCondition;
import org.moonzhou.spring.ioc.condition.injection.demo001os.condition.WindowsCondition;
import org.moonzhou.spring.ioc.condition.injection.demo001os.impl.LinuxShowCmd;
import org.moonzhou.spring.ioc.condition.injection.demo001os.impl.WinShowCmd;
import org.moonzhou.spring.ioc.condition.injection.demo002datasource.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * 功能描述: 代码方式配置bean<br>
 *
 * @author moon-zhou
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@Configuration
public class JavaConfig {

    @Bean("showCmd")
    @Conditional(WindowsCondition.class)
    ShowCmd winCmd() {
        return new WinShowCmd();
    }

    @Bean("showCmd")
    @Conditional(LinuxCondition.class)
    ShowCmd linuxCmd() {
        return new LinuxShowCmd();
    }

    /************************************************************/

    @Bean("ds")
    @Profile("dev")
    DataSource devDataSource() {
        DataSource dataSource = new DataSource();
        dataSource.setUrl("jdbc:mysql://127.0.0.1:3306/dev");
        dataSource.setUsername("root");
        dataSource.setPassword("123");
        return dataSource;
    }
    @Bean("ds")
    @Profile("prod")
    DataSource prodDataSource() {
        DataSource dataSource = new DataSource();
        dataSource.setUrl("jdbc:mysql://192.158.222.33:3306/dev");
        dataSource.setUsername("root");
        dataSource.setPassword("root");
        return dataSource;
    }
}
