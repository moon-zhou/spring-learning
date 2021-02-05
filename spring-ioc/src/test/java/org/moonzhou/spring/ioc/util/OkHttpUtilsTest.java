package org.moonzhou.spring.ioc.util;

import okhttp3.*;
import org.jetbrains.annotations.NotNull;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;

public class OkHttpUtilsTest {

    /**
     * 直接调用okhttp
     */
    @Test
    public void test() throws InterruptedException {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .build();
        asynHttpTest(okHttpClient);
    }

    @Test
    public void test2() throws InterruptedException {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        OkHttpClient okHttpClient = ctx.getBean("okHttpClient", OkHttpClient.class);
        asynHttpTest(okHttpClient);
        return;
    }

    @Test
    public void test3() throws InterruptedException {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        OkHttpClient okHttpClient = ctx.getBean("okHttpClient2", OkHttpClient.class);
        asynHttpTest(okHttpClient);
    }

    private void asynHttpTest(OkHttpClient okHttpClient) throws InterruptedException {
        Request request = new Request.Builder()
                .get()
                .url("pic url")  // TODO
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                System.out.println(e.getMessage());
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                System.out.println("-----success...");
                FileOutputStream out = new FileOutputStream(new File("E:\\test\\123.jpg"));
                int len;
                byte[] buf = new byte[1024];
                InputStream is = response.body().byteStream();
                while ((len = is.read(buf)) != -1) {
                    out.write(buf, 0, len);
                }
                out.close();
                is.close();
            }
        });

        // enqueue是异步请求，所以主线程阻塞，以待http请求完成，否则看不到请求返回的图片
        TimeUnit.SECONDS.sleep(20);
    }

}