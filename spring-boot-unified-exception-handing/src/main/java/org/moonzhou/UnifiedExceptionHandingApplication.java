package org.moonzhou;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author moon-zhou <ayimin1989@163.com>
 * @version V1.0.0
 * @description
 * @date 2020/6/6 11:25
 * @since 1.0
 */
@SpringBootApplication
public class UnifiedExceptionHandingApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(UnifiedExceptionHandingApplication.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);
    }
}
