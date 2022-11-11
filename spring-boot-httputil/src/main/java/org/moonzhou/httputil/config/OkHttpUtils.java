package org.moonzhou.httputil.config;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.moonzhou.httputil.util.JsonUtils;

import java.io.File;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.moonzhou.httputil.config.RequestHelper.*;

public class OkHttpUtils {

    public static OkHttpClient getClient(boolean useProxy) {
        if (useProxy) {
            return ApplicationContextUtils.getBean("okHttpClientProxy", OkHttpClient.class);
        } else {
            return ApplicationContextUtils.getBean("okHttpClient", OkHttpClient.class);
        }
    }

    public static Response getResponse(String url, Map<String, String> queries, Map<String, String> headers) {
        Request request = RequestHelper.buildGetRequest(url, queries, headers);
        return execRequestWithHeader(request);
    }

    public static String get(String url, Map<String, String> queries, Map<String, String> headers) {
        Request request = RequestHelper.buildGetRequest(url, queries, headers);
        return execRequest(request);
    }

    public static <T> T get(String url, Map<String, String> queries, Map<String, String> headers, Class<T> clazz) {
        String result = get(url, queries, headers);
        return JsonUtils.fromJson(result, clazz);
    }

    public static <T> List<T> getList(String url, Map<String, String> queries, Map<String, String> headers, Class<T> clazz) {
        String result = get(url, queries, headers);
        return JsonUtils.fromJsonToList(result, clazz);
    }

    public static Map<String, Object> getMap(String url, Map<String, String> queries, Map<String, String> headers) {
        String result = get(url, queries, headers);
        return JsonUtils.fromJsonToMap(result);
    }

    public static String get(String url, Map<String, String> queries) {
        return get(url, queries, Collections.emptyMap());
    }

    public static <T> T get(String url, Map<String, String> queries, Class<T> clazz) {
        return get(url, queries, null, clazz);
    }

    public static String getWithProxy(String url, Map<String, String> queries, Map<String, String> headers) {
        Request request = buildGetRequest(url, queries, headers);
        return execRequestWithProxy(request);
    }

    public static Map<String, Object> getMapWithProxy(String url, Map<String, String> queries, Map<String, String> headers) {
        String result = getWithProxy(url, queries, headers);
        return JsonUtils.fromJsonToMap(result);
    }

    public static String post(String url, Map<String, String> params, Map<String, String> headers) {
        Request request = buildPostRequest(url, params, headers);
        return execRequest(request);
    }

    public static String post(String url, String json, Map<String, String> headers) {
        Request request = buildPostRequestJson(url, json, headers);
        return execRequest(request);
    }

    public static String post(String url, Map<String, String> params) {
        return post(url, params, Collections.emptyMap());
    }

    public static String postFormURLEncoded(String url, Map<String, String> params) {
        return postFormURLEncoded(url, params, Collections.emptyMap());
    }

    public static String postFormURLEncoded(String url, Map<String, String> params, Map<String, String> headers) {
        Request request = buildUrlencodedFormRequest(url, params, headers);
        return execRequest(request);
    }

    public static String post(String url, Map<String, String> params, Map<String, String> headers, MediaType mediaType) {
        Request request = RequestHelper.buildPostRequest(url, params, headers);
        if (mediaType.type().equals("multipart"))
            request = RequestHelper.buildMultipartFormRequest(url, params, headers);
        return RequestHelper.execRequest(request);
    }

    public static String post(String url, String fileKey, String fileName, File file, Map<String, String> headers) {
        Request request = buildPostFileRequest(url, fileKey, fileName, file, headers);
        return execRequest(request);
    }

    public static String post(String url, String fileKey, String fileName, File file, Map<String, String> params, Map<String, String> headers) {
        Request request = buildMultipartFormRequest(url, file, fileKey, fileName, params, headers);
        return execRequest(request);
    }

    public static String post(String url, String fileKey, File file, Map<String, String> params, Map<String, String> headers) {
        Request request = buildMultipartFormRequest(url, file, fileKey, file.getName(), params, headers);
        return execRequest(request);
    }

    public static String post(String url, String fileKey, List<File> files, Map<String, String> params, Map<String, String> headers) {
        Request request = buildMultipartFormRequest(url, fileKey, files, params, headers);
        return execRequest(request);
    }

    public static String postWithProxy(String url, Map<String, String> params, Map<String, String> headers) {
        Request request = buildPostRequest(url, params, headers);
        return execRequestWithProxy(request);
    }

    public static String put(String url, Map<String, String> params, Map<String, String> headers) {
        Request request = buildPutRequest(url, params, headers);
        return execRequest(request);
    }

    public static String put(String url, Map<String, String> params) {
        return put(url, params, Collections.emptyMap());
    }

    public static String putWithProxy(String url, Map<String, String> params, Map<String, String> headers) {
        Request request = buildPutRequest(url, params, headers);
        return execRequestWithProxy(request);
    }

    public static String delete(String url, Map<String, String> headers) {
        Request request = buildDeleteRequest(url, headers);
        return execRequest(request);
    }

    public static String deleteWithProxy(String url, Map<String, String> headers) {
        Request request = buildDeleteRequest(url, headers);
        return execRequestWithProxy(request);
    }
}
