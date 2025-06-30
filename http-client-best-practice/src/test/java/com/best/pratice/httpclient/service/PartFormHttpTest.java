package com.best.pratice.httpclient.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class PartFormHttpTest {
//    @Test
//    public void test1(){
//        String url = "http://localhost:8097/alm/createOrUpdateTask";
//        String jsonString = "{"
//                + "\"crm_id\":\"2\","
//                + "\"title\":\"这是标题\","
//                + "\"desc\":\"这是描述\","
//                + "\"assignee\":\"ones@novastar.tech\","
//                + "\"project_id\":\"455MnHvu2gkd6Elw\","
//                + "\"req_user\":\"wzh\","
//                + "\"req_dept\":\"流程IT\","
//                + "\"create_time\":\"2025-06-10 09:30:00\","
//                + "\"req_type\":\"新IC定制-厂商需求\","
//                + "\"tech_dept\":\"流程IT\","
//                + "\"product_line\":\"诺瓦云产品线\","
//                + "\"base_version\":\"V10\","
//                + "\"customer_name\":\"大华股份\","
//                + "\"support_user\":\"wzh\","
//                + "\"outgoing\":\"是\","
//                + "\"product_spec\":\"GTR\","
//                + "\"customer_level\":\"A\","
//                + "\"outgoing_time\":\"2025-06-10 09:30:00\","
//                + "\"custom_type\":\"软件定制\","
//                + "\"jt_type\":\"交通定制协议\","
//                + "\"accept\":\"通过\","
//                + "\"custom_reason\":\"reason:\","
//                + "\"require_finish_time\":\"2025-06-30 09:30:00\","
//                + "\"accept_plan_time\":\"2025-06-29 09:30:00\","
//                + "\"pm_user\":\"王锦\","
//                + "\"ba_user\":\"王锦\","
//                + "\"ba_desc\":\"这是一个desc\","
//                + "\"plan_workload\":3"
//                + "}";
//        JSONObject data = JSON.parseObject(jsonString);
//        File file = new File("D:\\workspace\\BestPratice\\ToolUtilAndBestPratice\\image2.jpeg");
//        CloseableHttpClient client = HttpClients.createDefault();
//        HttpPost httpPost = new HttpPost(url);
//
//        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
//
//        builder.addTextBody("data", jsonString, ContentType.TEXT_PLAIN);
//        builder.addTextBody("files", description, ContentType.TEXT_PLAIN);
//        builder.addBinaryBody(
//                "file",
//                file,
//                ContentType.APPLICATION_OCTET_STREAM,
//                file.getName()
//        );
//    }

    
}
