package org.moonzhou.offline;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class SpringBootOfflineApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootOfflineApplication.class, args);
    }

}
