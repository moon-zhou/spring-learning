package org.moonzhou.init.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 *
 * ApplicationRunner执行的时机是：spring容器启动完成之后，就会紧接着执行这个接口实现类的run方法。
 * 该实现类可以有多个，通过@Order(1)来进行顺序的控制。
 * @author moon zhou
 * @version 1.0
 * @description:
 * @date 2022/10/18 15:41
 */
@Slf4j
@Component
public class MyApplicationRunner implements ApplicationRunner {

    @Value("${spring.profiles.active}")
    private String active;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("init my app config started...");
        log.info("spring env is ok, run my app runner, and active is: {}.", active);
        log.info("init my app config completed.");
    }
}
