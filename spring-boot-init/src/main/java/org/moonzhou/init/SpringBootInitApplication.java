package org.moonzhou.init;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author moon-zhou <ayimin1989@163.com>
 * @version V1.0.0
 * @description
 * @date 2022/10/18 15:35
 * @since 1.0
 */
@SpringBootApplication
public class SpringBootInitApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(SpringBootInitApplication.class);
        // app.setBannerMode(Banner.Mode.OFF);
        app.run(args);
    }
}
