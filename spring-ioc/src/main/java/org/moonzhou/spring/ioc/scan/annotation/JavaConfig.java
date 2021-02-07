package org.moonzhou.spring.ioc.scan.annotation;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;

/**
 * 使用包扫描：注解
 */
@Configuration
// 包位置扫描
//@ComponentScan(basePackages = "org.moonzhou.spring.ioc.scan.annotation")
// 注解类型扫描
@ComponentScan(basePackages = "org.moonzhou.spring.ioc.scan.annotation",
        useDefaultFilters = true, excludeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Controller.class)})
public class JavaConfig {
}