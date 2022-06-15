package org.moonzhou.threadpool.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author moonzhou
 * @description 线程池参数配置
 **/
@Data
@Configuration
@ConfigurationProperties(prefix = "moon.thread-pool")
public class ThreadPoolConfig {

    private int corePoolSize;
    private int maxPoolSize;
    private int queueCapacity;
}
