package org.moonzhou.httputil.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.time.Duration;

@Getter
@Setter
@ConfigurationProperties(prefix = "moon.util.http.okhttp3")
public class HttpProperties {
    private Duration connectTimeout = Duration.ofSeconds(15);
    private Duration readTimeout = Duration.ofSeconds(15);
    private Proxy proxy;
    private Boolean isRetry = true;
    private Pool pool = new Pool();

    @Getter
    @Setter
    public static class Proxy {
        private String host;
        private Integer port;
        private String username;
        private String password;
    }

    @Getter
    @Setter
    public static class Pool {
        private Integer maxIdle = 5;
        private Duration maxWait = Duration.ofMinutes(5);
    }
}