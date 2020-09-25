package org.moonzhou;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * @author moon-zhou <ayimin1989@163.com>
 * @version V1.0.0
 * @description
 * @date 2020/9/24 18:40
 * @since 1.0
 */
@ServletComponentScan(basePackages = {"org.moonzhou.filter.auto"})
@SpringBootApplication
public class WebThreadLocalApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(WebThreadLocalApplication.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);
    }
}
