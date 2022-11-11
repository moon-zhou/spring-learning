package org.moonzhou.httputil.config;

import lombok.extern.slf4j.Slf4j;
import okhttp3.ConnectionPool;
import okhttp3.Credentials;
import okhttp3.OkHttpClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.net.ssl.*;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Socket;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

/**
 * @author moon zhou
 * @version 1.0
 * @description Okhttp3 client config
 * @date 2022/11/10 13:51
 */
@Slf4j
@Configuration
@EnableConfigurationProperties(HttpProperties.class)
public class OkHttpConfig {
    @Bean
    public X509ExtendedTrustManager x509ExtendedTrustManager() {
        return new X509ExtendedTrustManager() {
            @Override
            public void checkClientTrusted(X509Certificate[] chain, String authType, Socket socket) throws CertificateException {

            }

            @Override
            public void checkServerTrusted(X509Certificate[] chain, String authType, Socket socket) throws CertificateException {

            }

            @Override
            public void checkClientTrusted(X509Certificate[] chain, String authType, SSLEngine engine) throws CertificateException {

            }

            @Override
            public void checkServerTrusted(X509Certificate[] chain, String authType, SSLEngine engine) throws CertificateException {

            }

            @Override
            public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {

            }

            @Override
            public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {

            }

            @Override
            public X509Certificate[] getAcceptedIssuers() {
                return new X509Certificate[0];
            }
        };
    }

    @Bean
    public SSLSocketFactory sslSocketFactory(X509TrustManager x509TrustManager) {
        try {
            // 信任任何链接
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, new TrustManager[]{x509TrustManager}, new SecureRandom());
            return sslContext.getSocketFactory();
        } catch (NoSuchAlgorithmException | KeyManagementException e) {
            log.error("sslSocketFactory error: ", e);
        }
        return null;
    }

    @Bean
    public ConnectionPool pool(HttpProperties httpProperties) {
        HttpProperties.Pool pool = httpProperties.getPool();
        int maxIdle = pool.getMaxIdle();
        Duration maxWait = pool.getMaxWait();
        long millis = maxWait.toMillis();
        return new ConnectionPool(maxIdle, millis, TimeUnit.MILLISECONDS);
    }

    @Bean("okHttpClient")
    public OkHttpClient okHttpClient(ConnectionPool pool, SSLSocketFactory sslSocketFactory,
                                     X509TrustManager x509TrustManager, HttpProperties httpProperties) {
        long connectTimeout = httpProperties.getConnectTimeout().toMillis();
        long readTimeout = httpProperties.getReadTimeout().toMillis();
        return new OkHttpClient.Builder()
                .sslSocketFactory(sslSocketFactory, x509TrustManager)
                .retryOnConnectionFailure(httpProperties.getIsRetry()) // 是否开启重试
                .connectionPool(pool) // 连接池
                .connectTimeout(connectTimeout, TimeUnit.MILLISECONDS)
                .readTimeout(readTimeout, TimeUnit.MILLISECONDS)
                .build();
    }

    @Bean("okHttpClientProxy")
    @ConditionalOnProperty(
            prefix = "moon.util.http.okhttp3.proxy",
            name = {"host", "port"}
    )
    public OkHttpClient okHttpClientProxy(ConnectionPool pool, SSLSocketFactory sslSocketFactory,
                                          X509TrustManager x509TrustManager, HttpProperties httpProperties) {
        HttpProperties.Proxy proxy = httpProperties.getProxy();
        String host = proxy.getHost();
        Integer port = proxy.getPort();
        String username = proxy.getUsername();
        String password = proxy.getPassword();
        long connectTimeout = httpProperties.getConnectTimeout().toMillis();
        long readTimeout = httpProperties.getReadTimeout().toMillis();

        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .sslSocketFactory(sslSocketFactory, x509TrustManager)
                .retryOnConnectionFailure(httpProperties.getIsRetry()) // 是否开启重试
                .connectionPool(pool) // 连接池
                .connectTimeout(connectTimeout, TimeUnit.MILLISECONDS)
                .readTimeout(readTimeout, TimeUnit.MILLISECONDS)
                .proxy(new Proxy(Proxy.Type.HTTP, new InetSocketAddress(host, port)));
        if (username != null && password != null) {
            String credential = Credentials.basic(username, password);
            builder.proxyAuthenticator((route, response) ->
                    response.request()
                            .newBuilder()
                            .header("Proxy-Authorization", credential)
                            .build());
        }
        return builder.build();
    }
}
