package com.best.pratice.httpClient.retry.iml;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.best.pratice.httpClient.retry.RetryStrategy;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * 重试方式的一种：循环重试
 */
public class DefaultRetryStrategy implements RetryStrategy {

    private static final int MAX_RETRIES = 3;
    private static final boolean RETRY_REQUESTS_WITH_BODY = true;  // 修改为true以支持带请求体的重试

    private static final long RETRY_INTERVAL_MS = 1000; // 添加重试间隔时间，单位毫秒


    /**
     * HttpClient自带的默认重试策略
     * 通过HttpClients.custom()
     *              .setRetryHandler(new DefaultHttpRequestRetryHandler(重试次数, 是否支持带请求体的重试))
     *              .build()
     * I.注意默认重试策略的第二个参数
     * - true:当消息离开客户端后仍报错IOException时，会再次尝试发送消息【适用于幂等的请求，如saveOrUpdate】
     * - false:当失败消息利尻客户端报错IOException时不进行重试【适用于非幂等的请求吗，如createOrder】
     * II.注意重试间隔
     * - 默认的重试策略没有重试间隔，即失败发生后立即进行重试
     * III.注意重试的发生条件
     * - 只有当发生了IOException和InterruptedIOException(后者是前者的子类)的情况下才会发生重试
     *
     * @param url
     * @param requestBody
     * @return JSONObject
     * @throws IOException
     */
    @Override
    public JSONObject retryPost(String url, String requestBody) throws IOException {

        try (CloseableHttpClient httpClient = HttpClients.custom()
                .setRetryHandler(new DefaultHttpRequestRetryHandler(MAX_RETRIES, RETRY_REQUESTS_WITH_BODY))
                .build()) {

            HttpPost httpPost = new HttpPost(url);
            httpPost.setHeader("Authentication", "Bareer AOSHNDIFAUHSIDU");
            httpPost.setHeader("Content-Type", "application/json");

            // 设置请求体
            if (requestBody != null) {
                httpPost.setEntity(new StringEntity(requestBody, StandardCharsets.UTF_8));
            }

            try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
                int statusCode = response.getStatusLine().getStatusCode();
                if (statusCode >= 200 && statusCode < 300) {
                    String responseString = EntityUtils.toString(response.getEntity());
                    return JSONObject.parseObject(responseString);
                } else {
                    throw new IOException("Request failed with status code: " + statusCode);
                }
            }
        }
    }

    public static void main(String[] args) {
        DefaultRetryStrategy retryStrategy = new DefaultRetryStrategy();
        String url = "http://172.16.81.31:8060/tycServer/esbAuthRestful/getToken";
        JSONObject response = null;
        try {
            // 示例请求体，根据实际API需要修改

            JSONObject bodyObj = new JSONObject()
                    .fluentPut("username", "ESB@SRM")
                    .fluentPut("password", "NOVAESB@SRM2024");
            response = retryStrategy.retryPost(url, JSON.toJSONString(bodyObj));
            System.out.println("SUCCESSFUL REQUEST!,response:"+response.toJSONString());
        } catch (IOException e) {
            System.out.println("ERROR OCCURRED! unexpected IOException : " + e.getMessage());
        }
        if (response != null) {
            System.out.println("Response: " + response);
        } else {
            System.out.println("Failed to get response from " + url);
        }
    }
}