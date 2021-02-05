package org.moonzhou.spring.ioc.util;

import okhttp3.OkHttpClient;

/**
 * OkHttpClient 的静态工厂
 */
public class OkHttpUtils {
    private static OkHttpClient OkHttpClient;

    public static OkHttpClient getInstance() {
        if (OkHttpClient == null) {
            OkHttpClient = new OkHttpClient.Builder().build();
        }
        return OkHttpClient;
    }
}