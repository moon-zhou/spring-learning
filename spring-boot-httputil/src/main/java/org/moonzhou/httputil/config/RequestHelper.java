package org.moonzhou.httputil.config;

import okhttp3.*;
import org.apache.tika.Tika;
import org.moonzhou.httputil.exception.HttpException;
import org.moonzhou.httputil.util.JsonUtils;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class RequestHelper {

    static Request buildGetRequest(String url, Map<String, String> params, Map<String, String> headers) {
        StringBuilder sb = new StringBuilder(url);
        if (params != null && params.size() > 0) {
            boolean firstFlag = true;
            for (Map.Entry<String, String> entry : params.entrySet()) {
                if (firstFlag) {
                    sb.append("?")
                            .append(entry.getKey())
                            .append("=")
                            .append(entry.getValue());
                    firstFlag = false;
                } else {
                    sb.append("&")
                            .append(entry.getKey())
                            .append("=")
                            .append(entry.getValue());
                }
            }
        }
        Request.Builder builder = new Request.Builder();
        if (headers != null) {
            headers.forEach(builder::header);
        }
        return builder
                .get()
                .url(sb.toString())
                .build();
    }

    static Request buildPostRequest(String url, Map<String, String> params, Map<String, String> headers) {
        if (params == null) {
            params = Collections.emptyMap();
        }
        String json = JsonUtils.toJson(params);
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json);
        Request.Builder builder = new Request.Builder();
        if (headers != null) {
            headers.forEach(builder::header);
        }
        return builder
                .url(url)
                .post(body)
                .build();
    }

    static Request buildPostFileRequest(String url, String fileKey, String fileName, File file, Map<String, String> headers) {
        RequestBody fileBody = RequestBody.create(MediaType.parse("application/octet-stream"), file);
        MultipartBody body = new MultipartBody.Builder()
                .setType(MediaType.parse("multipart/form-data"))
                .addFormDataPart(fileKey, fileName, fileBody)
                .build();
        Request.Builder builder = new Request.Builder();
        if (headers != null) {
            headers.forEach(builder::header);
        }
        return builder
                .url(url)
                .post(body)
                .build();
    }

    static Request buildUrlencodedFormRequest(String url, Map<String, String> params, Map<String, String> headers) {
        FormBody.Builder builder = new FormBody.Builder();
        params.forEach((name, value) -> {
            builder.add(name, value);
        });
        Request.Builder requestBuilder = new Request.Builder()
                .url(url).post(builder.build());
        if (headers != null) {
            headers.forEach((name, value) -> {
                requestBuilder.addHeader(name, value);
            });
        }
        return requestBuilder.build();
    }

    static Request buildMultipartFormRequest(String url, File file,
                                             String fileKey, String fileName, Map<String, String> params, Map<String, String> headers) {
        MultipartBody.Builder mubuilder = new MultipartBody.Builder()
                .setType(MultipartBody.FORM);

        Iterator<Map.Entry<String, String>> iterator = params.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, String> entry = iterator.next();
            mubuilder.addPart(Headers.of("Content-Disposition", "form-data; name=\"" + entry.getKey() + "\""),
                    RequestBody.create(null, entry.getValue()));
        }
        String mimeType;
        try {
            Tika tika = new Tika();
            mimeType = tika.detect(file);
        } catch (IOException e) {
            throw new HttpException(e.getMessage(), e);
        }
        RequestBody fileBody = RequestBody.create(MediaType.parse(mimeType), file);
        mubuilder.addPart(Headers.of("Content-Disposition",
                "form-data; name=\"" + fileKey + "\"; filename=\"" + fileName + "\""),
                fileBody);
        RequestBody requestBody = mubuilder.build();
        Request.Builder builder = new Request.Builder();
        if (headers != null) {
            headers.forEach(builder::header);
        }
        return builder
                .url(url)
                .post(requestBody)
                .build();
    }

    static Request buildPostRequestJson(String url, String json, Map<String, String> headers) {
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json);
        Request.Builder builder = new Request.Builder();
        if (headers != null) {
            headers.forEach(builder::header);
        }
        return builder
                .url(url)
                .post(body)
                .build();
    }

    static Request buildPutRequest(String url, Map<String, String> params, Map<String, String> headers) {
        if (params == null) {
            params = Collections.emptyMap();
        }
        String json = JsonUtils.toJson(params);
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json);
        Request.Builder builder = new Request.Builder();
        if (headers != null) {
            headers.forEach(builder::header);
        }
        return builder
                .url(url)
                .put(body)
                .build();
    }

    static Request buildDeleteRequest(String url, Map<String, String> headers) {
        Request.Builder builder = new Request.Builder();
        if (headers != null) {
            headers.forEach(builder::header);
        }
        return builder
                .url(url)
                .delete()
                .build();
    }


    static String execRequest(Request request) {
        Response response = null;
        try {
            OkHttpClient client = ApplicationContextUtils.getBean("okHttpClient", OkHttpClient.class);
            response = client.newCall(request).execute();
            if (!response.isSuccessful()) {
                throw new HttpException("Call http request failed, status is " + response.code() +
                        ", error is " + response.message());
            }
            ResponseBody body = response.body();
            if (body == null) {
                throw new HttpException("Call http request failed, response body is empty.");
            }
            return body.string();
        } catch (Exception e) {
            throw new HttpException(e.getMessage(), e);
        } finally {
            if (response != null) {
                response.close();
            }
        }
    }

    static Response execRequestWithHeader(Request request) {
        try {
            OkHttpClient client = ApplicationContextUtils.getBean("okHttpClient", OkHttpClient.class);
            Response response = client.newCall(request).execute();
            if (!response.isSuccessful()) {
                throw new HttpException("Call http request failed, status is " + response.code() +
                        ", error is " + response.message());
            }
            return response;
        } catch (Exception e) {
            throw new HttpException(e.getMessage(), e);
        }
    }

    static String execRequestWithProxy(Request request) {
        Response response = null;
        try {
            OkHttpClient client = ApplicationContextUtils.getBean("okHttpClientProxy", OkHttpClient.class);
            response = client.newCall(request).execute();
            if (!response.isSuccessful()) {
                throw new HttpException("Call http request with proxy failed, status is " + response.code());
            }
            ResponseBody body = response.body();
            if (body == null) {
                throw new HttpException("Call http request failed, response body is empty.");
            }
            return body.string();
        } catch (Exception e) {
            throw new HttpException(e.getMessage(), e);
        } finally {
            if (response != null) {
                response.close();
            }
        }
    }

    static Request buildMultipartFormRequest(String url, Map<String, String> params, Map<String, String> headers) {
        MultipartBody.Builder muBuilder = (new MultipartBody.Builder()).setType(MultipartBody.FORM);
        for (Map.Entry<String, String> entry : params.entrySet()) {
            muBuilder.addPart(Headers.of("Content-Disposition", "form-data; name=\"" + entry.getKey() + "\""),
                    RequestBody.create(null, entry.getValue()));
        }
        MultipartBody multipartBody = muBuilder.build();
        Request.Builder builder = new Request.Builder();
        if (headers != null)
            headers.forEach(builder::header);
        return builder
                .url(url)
                .post(multipartBody)
                .build();
    }

    static Request buildMultipartFormRequest(String url, String fileKey, List<File> files, Map<String, String> params, Map<String, String> headers) {
        MultipartBody.Builder muBuilder = new MultipartBody.Builder()
                .setType(MultipartBody.FORM);
        for (Map.Entry<String, String> entry : params.entrySet()) {
            muBuilder.addPart(Headers.of("Content-Disposition", "form-data; name=\"" + entry.getKey() + "\""),
                    RequestBody.create(null, entry.getValue()));
        }
        for (File file : files) {
            String mimeType = "application/octet-stream";
            try {
                Tika tika = new Tika();
                mimeType = tika.detect(file);
            } catch (IOException ignored) {
            }
            RequestBody fileBody = RequestBody.create(MediaType.parse(mimeType), file);
            muBuilder.addPart(Headers.of("Content-Disposition",
                    "form-data; name=\"" + fileKey + "\"; filename=\"" + file.getName() + "\""),
                    fileBody);
        }
        RequestBody requestBody = muBuilder.build();
        Request.Builder builder = new Request.Builder();
        if (headers != null) {
            headers.forEach(builder::header);
        }
        return builder
                .url(url)
                .post(requestBody)
                .build();
    }
}
