package org.iiidev.utils;

import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;

/**
 * HttpUtil
 *
 * @author IIIDelay
 * @createTime 2023年04月18日 21:21:00
 */
public class HttpUtil {
    private static volatile OkHttpClient client = null;

    /**
     * newInstant: 单例模式创建
     *
     * @return OkHttpClient
     */
    public static OkHttpClient newInstant() {
        if (client == null) {
            synchronized (HttpUtil.class) {
                if (client == null) {
                    client = new OkHttpClient();
                }
            }
        }
        return client;
    }

    public static String okHttpGetForJson(String url, Map headerMap) throws IOException {
        OkHttpClient okHttpClient = newInstant();
        Request request = new Request.Builder()
            .url(url)
            .headers(Headers.of(headerMap == null ? Collections.emptyMap() : headerMap))
            .header("Content-Type", "application/json")
            .build();
        return okHttpClient.newCall(request).execute().body().string();
    }

    /**
     * main 测试...
     *
     * @param args args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        String url = "http://localhost:9901/admin/product/getCategory1";
        String s = okHttpGetForJson(url, null);
        System.out.println("s = " + s);
    }
}
