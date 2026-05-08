package com.best.practice.ai.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties("ai.native.zhi-pu")
public class ZhiPuClientProperties {

    private String baseUrl;

    private String modelName;

    private String apiKey;
}
