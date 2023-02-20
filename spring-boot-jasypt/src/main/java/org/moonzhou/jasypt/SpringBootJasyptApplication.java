package org.moonzhou.jasypt;

import org.moonzhou.jasypt.config.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * @author moonzhou
 */
@SpringBootApplication
@EnableConfigurationProperties(User.class)
public class SpringBootJasyptApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootJasyptApplication.class, args);
	}

}
