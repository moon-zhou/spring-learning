package org.moonzhou.mybatisplus;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author moonzhou
 */
@SpringBootApplication
@MapperScan("org.moonzhou.mybatisplus.dao")
public class SpringBootMyBatisPlusApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootMyBatisPlusApplication.class, args);
	}

}
