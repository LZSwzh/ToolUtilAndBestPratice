package com.best.pratice.httpClient.basicService;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.Consts;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;

/**
 * post请求的基本使用(未优化版本)
 */
@Service
public class PostHttpService {
    /**前置知识
     * MIME TYPE:多用途互联网邮件扩展类型，本协议最早用于邮件，后广泛在互联网使用；具体MIME类型可以去w3school寻找
     *      常用信息头                   实际含义        例子
     *     - MIME-Version             协议版本        1.0
     *     - Content-Type             内容类型   application/x-www-fprm-urlencoded,application/json
     *     - Content-Transfer-Encoding  编码类型   8bit,binary
     *     - Content-Disposition   内容排列方式
     *              1.上传文件时需要设置：
     *                  Content-Disposition:form-data;name="fileName";
     *                  filename = "文件路径"
 *                  2.下载文件时需要设置：
     *                  Content-Disposition:attachment;filename=URLEncoder.encode("xxx.zip","UTF-8");
     *
     *                  web服务器就是根据文件后缀找到对应的MIME TYPE，然后设值响应头为对应的MIME TYPE值(.png--->image/png)
     */

    /**
     * 前置知识
     *      form表单支持的MIME类型/content-Type类型
     *      1.application/x-www-form-urlencoded   常用与基本的表单信息提交
     *      2.multipart/form-data    常用于上传文件
     *      3.text/plain     常用于字符串？
     *
     */


    /**
     * 发送content-type为application/x-www-form-urlencoded类型的请求【默认的post】
     */
    public void postByUrlEncodedType() throws UnsupportedEncodingException {
        String url = "https://collect.alipay.com/dwcookie?biztype=common&eventid=clicked&productid=PC&spmAPos=a385";
        CloseableHttpClient client = HttpClients.createDefault();

        HttpPost httpPost = new HttpPost(url);
        //给post设置参数
        /**NameValuePair
         * 对应input标签的name和输入的值
         */

        UrlEncodedFormEntity urlEncodedForm = new UrlEncodedFormEntity(Arrays.<NameValuePair>asList(
                new BasicNameValuePair("prefix-phone-input","18403909715"),
                new BasicNameValuePair("loginPasswordInput", "!254545")
        ));
        httpPost.setEntity(urlEncodedForm);
        //伪装浏览器
        httpPost.addHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/128.0.0.0 Safari/537.36 Edg/128.0.0.0");
        CloseableHttpResponse response = null;
        Header[] all = httpPost.getAllHeaders();
        for(Header header:all) System.out.println(header.getName()+":"+header.getValue());
        try {
            response = client.execute(httpPost);
            //获取响应结果
            HttpEntity entity = response.getEntity();


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
     * 发送content-type为application/json类型的请求
     */
    public void postByJsonType() throws UnsupportedEncodingException {
        String url = "https://collect.alipay.com/dwcookie?biztype=common&eventid=clicked&productid=PC&spmAPos=a385";
        CloseableHttpClient client = HttpClients.createDefault();

        HttpPost httpPost = new HttpPost(url);
        /**给post设置参数*/
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("userName","wzh");

        StringEntity jsonEntity = new StringEntity(jsonObj.toString(),"UTF-8");
        httpPost.setEntity(jsonEntity);//todo 设置entity
        httpPost.setHeader(//TODO 设置content-type
                new BasicHeader("Content-Type",
                        "application/json;charset=utf-8"));
        jsonEntity.setContentEncoding(Consts.UTF_8.name());// todo 设置entity编码
        //伪装浏览器
        httpPost.addHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/128.0.0.0 Safari/537.36 Edg/128.0.0.0");
        CloseableHttpResponse response = null;
        Header[] all = httpPost.getAllHeaders();
        for(Header header:all) System.out.println(header.getName()+":"+header.getValue());
        try {
            response = client.execute(httpPost);
            //获取响应结果
            HttpEntity entity = response.getEntity();


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
     * 发送content-type为multipart/form-data类型的请求
     * 需要引入httpmime
     */
    public void postByMutipart() throws UnsupportedEncodingException {
        String url = "https://collect.alipay.com/dwcookie?biztype=common&eventid=clicked&productid=PC&spmAPos=a385";
        CloseableHttpClient client = HttpClients.createDefault();

        HttpPost httpPost = new HttpPost(url);
        /**给post设置参数*/
        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        builder.setCharset(Consts.UTF_8);
        builder.setContentType(ContentType.MULTIPART_FORM_DATA);//设置contenttype
        builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);//设置浏览器模式
        HttpEntity httpEntity = builder.addPart("自己需要传的参数", new FileBody(new File("d:\\abc.gif")))
                //通过file|byte[]|inputstream传递文件
                .addBinaryBody("自己需要传的参数", new File("d:\\abc.gif"))
                .addTextBody("除了文件的表单数据", "取值").build();
        //todo 如果表单有中文不能通过addTextbody添加，否则乱码,可以通过下面添加
        StringBody userName = new StringBody("username",ContentType.create("text/plain",Consts.UTF_8));
        httpPost.setEntity(httpEntity);//todo 设置entity
        //伪装浏览器
        httpPost.addHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/128.0.0.0 Safari/537.36 Edg/128.0.0.0");
        CloseableHttpResponse response = null;
        Header[] all = httpPost.getAllHeaders();
        for(Header header:all) System.out.println(header.getName()+":"+header.getValue());
        try {
            response = client.execute(httpPost);
            //获取响应结果
            HttpEntity entity = response.getEntity();


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
