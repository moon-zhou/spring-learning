package org.moonzhou.transaction;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author moon-zhou <ayimin1989@163.com>
 * @version V1.0.0
 * @description
 * @date 2022/10/19 11:09
 * @since 1.0
 */
@SpringBootApplication
public class SpringBootTransactionApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(SpringBootTransactionApplication.class);
        // app.setBannerMode(Banner.Mode.OFF);
        app.run(args);
    }
}
