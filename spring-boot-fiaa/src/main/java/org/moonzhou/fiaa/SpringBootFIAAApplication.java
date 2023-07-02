package org.moonzhou.fiaa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * @author moonzhou
 */
@ServletComponentScan
@SpringBootApplication
public class SpringBootFIAAApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootFIAAApplication.class, args);
	}

}
