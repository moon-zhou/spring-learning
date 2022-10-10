package org.moonzhou.event;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author moon-zhou <ayimin1989@163.com>
 * @version V1.0.0
 * @description
 * @date 2022/10/10 17:25
 * @since 1.0
 */
@SpringBootApplication
@EnableAsync
public class SpringBootEventApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(SpringBootEventApplication.class);
        // app.setBannerMode(Banner.Mode.OFF);
        app.run(args);
    }
}
