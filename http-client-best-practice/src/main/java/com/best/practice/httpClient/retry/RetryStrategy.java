package com.best.practice.httpClient.retry;


import com.alibaba.fastjson.JSONObject;

import java.io.IOException;

public interface RetryStrategy {

//    JSONObject retryGet(String url) throws IOException;

    JSONObject retryPost(String url, String requestBody) throws IOException;
}
