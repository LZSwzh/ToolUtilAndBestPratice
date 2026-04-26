package com.best.practice.config;

import lombok.Data;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@ConfigurationProperties("ai.native.zhi-pu")
public class ZhiPuClientProperties {

    private String baseUrl;

    private String modelName;

    private String apiKey;
}
