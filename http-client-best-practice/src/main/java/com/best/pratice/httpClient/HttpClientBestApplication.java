package com.best.pratice.httpClient;


import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;


@SpringBootApplication
public class HttpClientBestApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(HttpClientBestApplication.class, args);
        HttpClient client1 = (HttpClient) context.getBean("httpClient");
        HttpClient client2 = (HttpClient) context.getBean("httpClient");
        /**验证单例Bean*/
        System.out.println(client1==client2);//输出true

        try {
            /**验证长连接*/
            HttpGet getReq = new HttpGet("http://www.baidu.com");
//            getReq.addHeader("Connection","keep-alive");
            HttpResponse response = client1.execute(getReq);
            String connHeader = response.getFirstHeader("Connection").getValue();
            String keepAliveHeader = response.getFirstHeader("Keep-Alive").getValue();
            System.out.println("conn请求头:"+connHeader);
            System.out.println("keep-alive请求头:"+keepAliveHeader);
            /** 验证池化技术 */
            /** 验证池化技术 */
            PoolingHttpClientConnectionManager cm = (PoolingHttpClientConnectionManager) client1.getConnectionManager();
            System.out.println("总连接数: " + cm.getTotalStats().getAvailable() + ", 空闲连接数: " + cm.getTotalStats().getAvailable());

            // 发送多个请求
            for (int i = 0; i < 100; i++) {
                HttpGet getRequest = new HttpGet("http://www.example.com");
                HttpResponse rep = client1.execute(getRequest);
                System.out.println("执行了第 " + (i + 1) + " 次请求后连接池的状态");
                // 再次打印连接池状态
                System.out.println("总连接数: " + cm.getTotalStats().getAvailable() + ", 空闲连接数: " + cm.getTotalStats().getAvailable());
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
