# HttpClient使用最佳实践
##  一、最佳实践思路
高并发场景下的HttpClient优化方案
### 1.1.最佳实践之——池化


### 1.2.最佳实践之——长连接


### 1.3.最佳实践之—— client及req复用

### 1.4.最佳实践之——参数配置

### 1.5.异步

## 二、最佳实践案例案例

### 2.1.数据迁移项目
目前存在一个项目，需要基于Http发起请求从平台一获取全量数据，然后通过Http请求向平台二迁移数据。\
观察原始代码片段，发现存在较多的性能问题：
- 1.每次调用方法反复创建HttpClient，由于这个本身线程安全，只需全局保留一个即可
- 2.反复创建TCP连接，tcp需要三次握手四次挥手造成耗时，可以使用长连接来复用这个连接即可；或者使用池化技术优化
- 3.重复缓存entity的开销，每次调用都需要将内容再次复制一份
```java
        /**本片段是某个方法的一部分，大致作用是
         *          1.创建HttpClient
         *          2.创建请求对应HttpGet、或者Post等等
         *          3.设置请求参数
         *          4.执行请求
         *          5.获取响应并转换为实体类
         *          6.关闭相关资源          
         */
        //拼接URL
        String url = URI + "/rest/synapse/latest/public/testPlan/" + encodedKey + "/cycles";
        //
        try (CloseableHttpClient client = HttpClients.createDefault()) {

            HttpGet httpGet = new HttpGet(url);
            httpGet.setHeader(TOKEN_HEADER, jiraAuthService.getToken());

            try (CloseableHttpResponse response = client.execute(httpGet)) {
                int statusCode = response.getStatusLine().getStatusCode();
                if (statusCode != 200) {
                    log.error("请求测试周期失败，HTTP状态码：" + statusCode + "；key:" + planKey);
                    return null;
                }

                try {
                    HttpEntity entity = response.getEntity();
                    if (entity != null) {
                        String jsonStr = EntityUtils.toString(entity);
                        return JSON.parseObject(jsonStr, new TypeReference<List<TestCycle>>() {});
                    } else {
                        log.error("尝试获取所有测试周期，响应体为空；key:" + planKey);
                        return null;
                    }
                } catch (IOException e) {
                    log.error("解析响应体时发生错误", e);
                    throw new RuntimeException("响应体解析失败", e);
                }
            }
        } catch (IOException e) {
            log.error("执行HTTP请求时发生错误", e);
            throw new RuntimeException("HTTP请求失败", e);
        }
```
#### 优化1：client创建开销优化————单例Client




