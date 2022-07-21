package org.moonzhou.mybatisplus.ocnfig;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author moon zhou
 */
@Getter
@Setter
@ConfigurationProperties(
        prefix = "moon.mybatis.column-encrypt"
)
public class MybatisProperties {
    private String aesSecretKey;
}