package org.moonzhou.xss.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * mybatis配置类，主要是扫描mapper接口
 */
@Configuration
@MapperScan("org.moonzhou.xss.dao")
public class MyBatisConfig {

}