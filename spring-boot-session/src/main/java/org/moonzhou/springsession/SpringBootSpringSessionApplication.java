package org.moonzhou.springsession;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * @author moonzhou
 */
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 1800, redisNamespace = "moon:session")
@SpringBootApplication
public class SpringBootSpringSessionApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootSpringSessionApplication.class, args);
	}

}
