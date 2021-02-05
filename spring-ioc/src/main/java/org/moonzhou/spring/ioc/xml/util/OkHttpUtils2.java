package org.moonzhou.spring.ioc.xml.util;

import okhttp3.OkHttpClient;

/**
 * OkHttpClient 实例工厂
 */
public class OkHttpUtils2 {

    private OkHttpClient OkHttpClient;

    public OkHttpClient getInstance() {
        if (OkHttpClient == null) {
            OkHttpClient = new OkHttpClient.Builder().build();
        }
        return OkHttpClient;
    }
}