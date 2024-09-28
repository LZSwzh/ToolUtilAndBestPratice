package com.best.pratice.httpClient.basicService;

import org.apache.http.*;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Get请求基本使用
 */
@Service
public class GetHttpService {
    /**
     * 无参数get请求
     */
    public void testGetWihtourParam(){
        //可关闭的httpclient客户端，相当于一个浏览器
        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("http://www.baidu.com");
        CloseableHttpResponse response = null;
        try {
             response = client.execute(httpGet);
            //获取响应结果
            HttpEntity entity = response.getEntity();
            String jsonStr = EntityUtils.toString(entity, "utf-8");
            System.out.println(jsonStr);
            //确保流关闭
            EntityUtils.consume(entity);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally {
            //关闭客户端
            if (client!=null) {
                try {
                    client.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            //关闭响应
            if (response!=null){
                try {
                    response.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    /**
     * 携带请求头：
     *      User-Agent——用于伪造浏览器
     *      refer———————用于防盗链
     */
    public void testGetWithHeader(){
        //可关闭的httpclient客户端，相当于一个浏览器
        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("http://www.baidu.com");
        //伪装浏览器
        httpGet.addHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/128.0.0.0 Safari/537.36 Edg/128.0.0.0");
        //解决防盗链，值为对应网站的url
        httpGet.addHeader("Refer","http://www.baidu.com");
        CloseableHttpResponse response = null;
        try {
            response = client.execute(httpGet);
            //获取响应结果
            HttpEntity entity = response.getEntity();
            String jsonStr = EntityUtils.toString(entity, "utf-8");
            System.out.println(jsonStr);
            //确保流关闭
            EntityUtils.consume(entity);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally {
            //关闭客户端
            if (client!=null) {
                try {
                    client.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            //关闭响应
            if (response!=null){
                try {
                    response.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    /**
     * 带参数请求：
     *      表单提交时，浏览器会自动进行URL_ENCODE，web容器会自动解析
     *      将形如aaa+bbb====>aaa%2bbbb
     *      因此模拟的时候需要URLEncode
     */
    public void testGetWithParam(){
        //可关闭的httpclient客户端，相当于一个浏览器
        CloseableHttpClient client = HttpClients.createDefault();
        String param = null;
        try {
            param = URLEncoder.encode("自己的参数","UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        HttpGet httpGet = new HttpGet("http://www.xxx.com"+param);
        //伪装浏览器
        httpGet.addHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/128.0.0.0 Safari/537.36 Edg/128.0.0.0");
        //解决防盗链，值为对应网站的url
        httpGet.addHeader("Refer","http://www.baidu.com");
        CloseableHttpResponse response = null;
        try {
            response = client.execute(httpGet);
            //获取响应结果
            HttpEntity entity = response.getEntity();
            String jsonStr = EntityUtils.toString(entity, "utf-8");
            System.out.println(jsonStr);
            //确保流关闭
            EntityUtils.consume(entity);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally {
            //关闭客户端
            if (client!=null) {
                try {
                    client.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            //关闭响应
            if (response!=null){
                try {
                    response.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    /**
     * 获取响应头
     */
    public void testGetToGetResHeader(){
        //可关闭的httpclient客户端，相当于一个浏览器
        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("http://www.baidu.com");
        //伪装浏览器
        httpGet.addHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/128.0.0.0 Safari/537.36 Edg/128.0.0.0");
        //解决防盗链，值为对应网站的url
        httpGet.addHeader("Refer","http://www.baidu.com");
        CloseableHttpResponse response = null;
        try {
            response = client.execute(httpGet);
            //TODO 代表请求是否成功
            int code = response.getStatusLine().getStatusCode();
            if (HttpStatus.SC_OK == code){
                System.out.println("请求成功");
                //TODO 获取所有响应头
                Header[] allHeaders = response.getAllHeaders();
                for(Header header:allHeaders){
                    System.out.println("响应头："+header.getName()+"值为："+header.getValue());
                }
                
                //获取响应结果
                HttpEntity entity = response.getEntity();
                //todo 也可以通过entity获取响应类型
                Header contentType = entity.getContentType();
                String jsonStr = EntityUtils.toString(entity, "utf-8");
                System.out.println(jsonStr);
                //确保流关闭
                EntityUtils.consume(entity);
            }else {
                System.out.println("响应失败："+code);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally {
            //关闭客户端
            if (client!=null) {
                try {
                    client.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            //关闭响应
            if (response!=null){
                try {
                    response.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    /**
     * 将网络图片保存到本地
     */
    public void testGetDownImage(){
        String url = "https://www.runoob.com/wp-content/uploads/2014/01/2243690-9cd9c896e0d512ed.gif";
        CloseableHttpClient client = HttpClients.createDefault();

        HttpGet httpGet = new HttpGet(url);
        //伪装浏览器
        httpGet.addHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/128.0.0.0 Safari/537.36 Edg/128.0.0.0");
        CloseableHttpResponse response = null;
        try {
            response = client.execute(httpGet);
            //获取响应结果
            HttpEntity entity = response.getEntity();
            String contentType = entity.getContentType().getValue();
            String suffix = ".jpg";
            if (contentType.contains("jpg") || contentType.contains("jpeg")){
                suffix = ".jpg";
            }else if (contentType.contains("bmp") || contentType.contains("bitmap")){
                suffix = ".bmp";
            }else if (contentType.contains("png")){
                suffix = ".png";
            }else if (contentType.contains("gif")){
                suffix = ".gif";
            }
            byte[] bytes = EntityUtils.toByteArray(entity);
            String localStr = "D:\\abc"+suffix;

            FileOutputStream out = new FileOutputStream(localStr);
            out.write(bytes);
            out.close();
            //确保流关闭
            EntityUtils.consume(entity);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally {
            //关闭客户端
            if (client!=null) {
                try {
                    client.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            //关闭响应
            if (response!=null){
                try {
                    response.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
    /**
     * 设置访问代理
     *      爬虫高频访问一个网站，如果网站有反爬技术，可能会封掉IP
     *      可以搞些具有静态IP的机器，通过轮询去访问，所以可以使用访问代理
     *    这里只是举个例子，稳定的代理通常需要交钱
     *    可以通过66ip.cn获取一些静态IP
     */
    public void testGetByProxy(){
        String url = "https://www.runoob.com/wp-content/uploads/2014/01/2243690-9cd9c896e0d512ed.gif";
        CloseableHttpClient client = HttpClients.createDefault();

        HttpGet httpGet = new HttpGet(url);
        //对请求进行配置
        String ip = "ip";
        Integer port = 80;
        httpGet.setConfig(RequestConfig.custom()
                .setProxy(new HttpHost(ip,port))//填ip、端口.
                .build());
        //伪装浏览器
        httpGet.addHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/128.0.0.0 Safari/537.36 Edg/128.0.0.0");
        CloseableHttpResponse response = null;
        try {
            response = client.execute(httpGet);
            //获取响应结果
            HttpEntity entity = response.getEntity();
            String contentType = entity.getContentType().getValue();
            String suffix = ".jpg";
            if (contentType.contains("jpg") || contentType.contains("jpeg")){
                suffix = ".jpg";
            }else if (contentType.contains("bmp") || contentType.contains("bitmap")){
                suffix = ".bmp";
            }else if (contentType.contains("png")){
                suffix = ".png";
            }else if (contentType.contains("gif")){
                suffix = ".gif";
            }
            byte[] bytes = EntityUtils.toByteArray(entity);
            String localStr = "D:\\abc"+suffix;

            FileOutputStream out = new FileOutputStream(localStr);
            out.write(bytes);
            out.close();
            //确保流关闭
            EntityUtils.consume(entity);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally {
            //关闭客户端
            if (client!=null) {
                try {
                    client.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            //关闭响应
            if (response!=null){
                try {
                    response.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    /**
     * 设置访问代理
     * 连接超时和读取超时
     */
    public void testGetByConnecTimeoutAndReadTimeOut(){
        String url = "https://www.runoob.com/wp-content/uploads/2014/01/2243690-9cd9c896e0d512ed.gif";
        CloseableHttpClient client = HttpClients.createDefault();

        HttpGet httpGet = new HttpGet(url);
        //对请求进行配置
        String ip = "ip";
        Integer port = 80;
        httpGet.setConfig(RequestConfig.custom()
//                .setProxy(new HttpHost(ip,port))//填ip、端口.
                .setConnectTimeout(5000)//ms,连接超时,即三次握手的时间上限
                .setSocketTimeout(3000)//ms,读取超时,从网址获取响应的时间间隔上限
                .build());
        //伪装浏览器
        httpGet.addHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/128.0.0.0 Safari/537.36 Edg/128.0.0.0");
        CloseableHttpResponse response = null;
        try {
            response = client.execute(httpGet);
            //获取响应结果
            HttpEntity entity = response.getEntity();
            String contentType = entity.getContentType().getValue();
            String suffix = ".jpg";
            if (contentType.contains("jpg") || contentType.contains("jpeg")){
                suffix = ".jpg";
            }else if (contentType.contains("bmp") || contentType.contains("bitmap")){
                suffix = ".bmp";
            }else if (contentType.contains("png")){
                suffix = ".png";
            }else if (contentType.contains("gif")){
                suffix = ".gif";
            }
            byte[] bytes = EntityUtils.toByteArray(entity);
            String localStr = "D:\\abc"+suffix;

            FileOutputStream out = new FileOutputStream(localStr);
            out.write(bytes);
            out.close();
            //确保流关闭
            EntityUtils.consume(entity);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally {
            //关闭客户端
            if (client!=null) {
                try {
                    client.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            //关闭响应
            if (response!=null){
                try {
                    response.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

}
