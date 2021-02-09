package org.moonzhou.spring.ioc.injection;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;

/**
 * 使用包扫描：注解
 */
@Configuration
@ComponentScan(basePackages = "org.moonzhou.spring.ioc.injection")
public class JavaConfig {
}