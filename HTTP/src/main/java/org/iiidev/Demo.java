package org.iiidev;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.iiidev.common.exception.ServiceRuntimeException;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * Demo
 *
 * @author IIIDelay
 * @createTime 2023年04月19日 23:31:00
 */
public class Demo {
    public static void main(String[] args) {
        String string = ContentType.APPLICATION_JSON.toString();
        System.out.println("string = " + string);

        String string1 = ContentType.APPLICATION_FORM_URLENCODED.toString();
    }

    private static String doPost(String url, String body) {
        String result = "";
        try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
            // 请求头, 请求体, 请求配置信息...都是放在 httpPost 中
            URIBuilder uriBuilder = new URIBuilder(url).addParameter("name", "张三");

            HttpPost httpPost = new HttpPost(uriBuilder.build());
            RequestConfig requestConfig  = RequestConfig
                .custom()
                .setConnectTimeout(3000)
                .build();
            httpPost.setConfig(requestConfig);
            httpPost.addHeader(HttpHeaders.CONTENT_TYPE, ContentType.APPLICATION_JSON.toString());

            HttpEntity httpEntity = new StringEntity(body);
            httpPost.setEntity(httpEntity);

            HttpEntity entity = httpclient.execute(httpPost).getEntity();
            result = EntityUtils.toString(entity, StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new ServiceRuntimeException(e);
        }
        return result;
    }

    private static String doGetForParm(String url) {
        String result = "";
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            // 通过 URIBuilder 构建url与请求参数
            URIBuilder uriBuilder = new URIBuilder(url)
                .addParameter("q", "西游记");
            System.out.println("uriBuilder.toString() = " + uriBuilder.toString());

            HttpGet httpGet = new HttpGet(uriBuilder.build());

            HttpEntity entity = httpClient.execute(httpGet).getEntity();

            result = EntityUtils.toString(entity, "UTF-8");
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
        return result;
    }

    private static String doGetForJson(String url) {
        String result = "";
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet httpGet = new HttpGet(url);
            System.out.println("httpGet.getRequestLine() = " + httpGet.getRequestLine());

            // 发送请求, 并返回响应
            CloseableHttpResponse httpResponse = httpClient.execute(httpGet);

            // 获取响应状态码 200|404等
            int statusCode = httpResponse.getStatusLine().getStatusCode();

            // 获取响应实体
            HttpEntity entity = httpResponse.getEntity();
            // 响应实体 utf-8 解析
            result = EntityUtils.toString(entity, "utf-8");
            // result= entity.toString(); // 不能指定编码格式
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
            throw new RuntimeException(exception);
        }
        return result;
    }
}
