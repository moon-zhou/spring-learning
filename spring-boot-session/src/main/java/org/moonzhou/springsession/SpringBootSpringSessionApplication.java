package org.moonzhou.springsession;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author moonzhou
 */
// @EnableRedisHttpSession(maxInactiveIntervalInSeconds = 1800, redisNamespace = "moon:session")
@SpringBootApplication
public class SpringBootSpringSessionApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootSpringSessionApplication.class, args);
	}

}
