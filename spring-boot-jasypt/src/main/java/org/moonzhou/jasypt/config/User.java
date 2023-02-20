package org.moonzhou.jasypt.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author
 * @version 1.0
 * @description: TODO
 * @date 2023/2/20 21:26
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "user")
public class User {
    private String name;
    private int age;
    private String desc;
}
