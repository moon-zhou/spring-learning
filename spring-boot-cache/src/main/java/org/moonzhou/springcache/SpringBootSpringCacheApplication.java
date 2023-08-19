package org.moonzhou.springcache;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * @author moonzhou
 */
@EnableCaching
@SpringBootApplication
public class SpringBootSpringCacheApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootSpringCacheApplication.class, args);
	}

}
