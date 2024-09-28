package com.best.pratice.httpClient.config;


import org.apache.http.HeaderElement;
import org.apache.http.HeaderElementIterator;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeaderElementIterator;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;


@Configuration
public class HttpClientConfig {


    @Bean
    @Scope("singleton")
    public ConnectionKeepAliveStrategy longConnStrategy(){
        /**HttpClient自带ConnectionKeepAliveStrategy接口可以配置长连接策略*/
        ConnectionKeepAliveStrategy myStrategy = new ConnectionKeepAliveStrategy() {
            //配置长连接活动时长,如果请求有这个参数,就将timeout的毫秒数转为秒,如果没就默认60s
            public long getKeepAliveDuration(HttpResponse response, HttpContext context) {
                HeaderElementIterator it = new BasicHeaderElementIterator(response.headerIterator(HTTP.CONN_KEEP_ALIVE));
                while (it.hasNext()) {
                    HeaderElement he = it.nextElement();
                    String param = he.getName();
                    String value = he.getValue();
                    if (value != null && param.equalsIgnoreCase("timeout")) {
                        return Long.parseLong(value) * 1000;
                    }
                }
                return 60 * 1000;//如果没有约定，则默认定义时长为60s
            }
        };
        return myStrategy;
    }

    @Bean
    @Scope("singleton")
    public PoolingHttpClientConnectionManager connectionManager(){
        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
        connectionManager.setMaxTotal(500);//连接池总数
        connectionManager.setDefaultMaxPerRoute(50);//每个路由允许的连接上限
        return connectionManager;
    }
    /**
     * HttpClient本身是线程安全的,因此这里直接配置一个单例Bean,避免频繁创建和销毁
     */
    @Bean
    @Scope("singleton")
    public CloseableHttpClient httpClient(){
//        return HttpClients.createDefault();
        return HttpClients.custom()
                .setConnectionManager(connectionManager())//设置连接管理器
                .setKeepAliveStrategy(longConnStrategy())//设置长连接策略
                .setDefaultRequestConfig(//设置请求配置
                    RequestConfig.custom()
                        .setStaleConnectionCheckEnabled(true)//逐出已被关闭的链接
                        .build()
                ).build();
    }
}
