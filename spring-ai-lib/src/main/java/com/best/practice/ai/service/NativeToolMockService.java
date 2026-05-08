package com.best.practice.ai.service;

import ai.z.openapi.ZhipuAiClient;
import ai.z.openapi.service.model.*;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
@RequiredArgsConstructor
public class NativeToolMockService {

    private final ZhipuAiClient zhipuAiClient;

    private static Map<String,ChatTool> CHAT_TOOL_REL = new ConcurrentHashMap<>();

    @PostConstruct
    public void initChatToolRel(){
        CHAT_TOOL_REL.put("weather", buildWeatherTool());
    }

    // 模拟天气 API
    public Map<String, Object> getWeather(String location, String date) {
        Map<String, Object> weather = new HashMap<>();
        weather.put("location", location);
        weather.put("date", date != null ? date : "今天");
        weather.put("weather", "晴天");
        weather.put("temperature", "25°C");
        weather.put("humidity", "60%");
        return weather;
    }

    private ChatTool buildWeatherTool(){
        // 定义函数工具
        Map<String, ChatFunctionParameterProperty> properties = new HashMap<>();
        // 构造参数  位置
        ChatFunctionParameterProperty locationProperty = ChatFunctionParameterProperty
                .builder().type("string").description("City name, for example: Beijing").build();
        properties.put("location", locationProperty);
        // 构造参数 摄氏度、华氏度
        ChatFunctionParameterProperty unitProperty = ChatFunctionParameterProperty
                .builder().type("string").enums(Arrays.asList("celsius", "fahrenheit")).build();
        properties.put("unit", unitProperty);

        // 构造工具
        // - WEB_SEARCH("web_search"), 网络搜索
        // - RETRIEVAL("retrieval"), 数据源检索
        // - FUNCTION("function"), 函数调用
        // - MCP("mcp"); mcp调用
        return ChatTool.builder()
                .type(ChatToolType.FUNCTION.value())
                .function(
                    ChatFunction
                        .builder()
                        .name("get_weather")
                        .description("获取指定地点的天气信息")
                        .parameters(
                            ChatFunctionParameters.builder()
                            .type("object")
                            .properties(properties)
                            .build()
                        )
                        .build()
                )
                .build();

    }
    public ChatTool getTollByName(String toolName){
        ChatTool chatTool = CHAT_TOOL_REL.get(toolName);
        if (null==chatTool){
            throw new RuntimeException("无效工具名称");
        }
        return chatTool;
    }


}
