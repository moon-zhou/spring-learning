/*
 * Copyright (C), 2002-2021, moon-zhou
 * FileName: Security.java
 * Author:   moon-zhou
 * Email:    ayimin1989@163.com
 * Date:     2021/6/10 11:03
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名    修改时间    版本号       描述
 */
package org.moonzhou.profile.config.bean;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;


/**
 * 功能描述: 自定义配置文件示例<br>
 *
 * @author moon-zhou
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "system-info")
public class SystemInfo {
    private String code;

    private String name;
}
