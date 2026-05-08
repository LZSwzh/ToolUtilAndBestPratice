package com.best.practice.ai.config;

import ai.z.openapi.ZhipuAiClient;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Slf4j
@Configuration
@RequiredArgsConstructor
@EnableConfigurationProperties(ZhiPuClientProperties.class)
public class SdkClientConfig {

    private final ZhiPuClientProperties zhiPuClientProperties;

    @PostConstruct
    void init(){
      log.info("智谱JAVA SDK 加载完成,url:{}；key:{}",zhiPuClientProperties.getBaseUrl(),zhiPuClientProperties.getApiKey());
    }

    @Bean
    public ZhipuAiClient zhipuAiClient(ZhiPuClientProperties zhiPuClientProperties){
        return ZhipuAiClient.builder()
                .baseUrl(zhiPuClientProperties.getBaseUrl())
                .apiKey(zhiPuClientProperties.getApiKey())
                .build();
    }
}
