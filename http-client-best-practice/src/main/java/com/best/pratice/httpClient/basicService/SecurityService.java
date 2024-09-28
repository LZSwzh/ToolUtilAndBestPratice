package com.best.pratice.httpClient.basicService;

import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.TrustStrategy;
import org.springframework.stereotype.Service;

import javax.net.ssl.SSLContext;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;

/**
 * 如何绕过https安全认证
 *      有时候网页会提示你的连接不是私密链接
 */
@Service
public class SecurityService {
    /**
     *方法1：通过认证需要的密钥配置client
     */
    public void passBySecret(){

    }
    /**
     * 方法2:绕过https安全认证
     */
    public void passBySkipAuth() throws NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        HttpClientBuilder builder = HttpClients.custom().setConnectionManager(
                new PoolingHttpClientConnectionManager(
                        RegistryBuilder.<ConnectionSocketFactory>create()
                                .register("http", PlainConnectionSocketFactory.INSTANCE)
                                .register("https", trustHttpsCertificate())//通过自定义方法信任HTTPS的证书
                                .build()
                )
        );

        CloseableHttpClient client = builder.build();
        //......

    }

    /**
     * 创建支持协议的安全工厂
     * @return
     * @throws NoSuchAlgorithmException
     * @throws KeyManagementException
     * @throws KeyStoreException
     */
    private ConnectionSocketFactory trustHttpsCertificate() throws NoSuchAlgorithmException, KeyManagementException, KeyStoreException {
        SSLContextBuilder sslContextBuilder = new SSLContextBuilder();
        //
        SSLContext sslContext = sslContextBuilder.loadTrustMaterial(null, new TrustStrategy() {
            //判断是否信任url
            public boolean isTrusted(X509Certificate[] x509Certificates, String s) {
                return true; // 这里总是信任所有证书，实际使用时需要更严格的逻辑
            }
        }).build();


        SSLConnectionSocketFactory sslConnectionSocketFactory =
                new SSLConnectionSocketFactory(sslContext,
                        new String[]{"SSLv2Hello","SSLv3","TLSv1","TLSv1.1","TLSv1.2"},
                        null,
                        NoopHostnameVerifier.INSTANCE);
        return sslConnectionSocketFactory;

    }
}
