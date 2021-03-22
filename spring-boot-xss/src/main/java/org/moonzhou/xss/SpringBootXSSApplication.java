package org.moonzhou.xss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class SpringBootXSSApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootXSSApplication.class, args);
    }

}
