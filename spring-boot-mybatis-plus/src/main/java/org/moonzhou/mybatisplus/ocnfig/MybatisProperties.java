package org.moonzhou.mybatisplus.ocnfig;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author moon zhou
 */
@Getter
@Setter
@Configuration
@ConfigurationProperties(
        prefix = "moon.mybatis.column-encrypt"
)
public class MybatisProperties {
    private String aesSecretKey;
}