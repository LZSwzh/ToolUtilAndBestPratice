package com.best.practice.ai.service.tool;

import com.fasterxml.jackson.annotation.JsonClassDescription;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Description;
import org.springframework.stereotype.Service;

import java.util.function.Function;

/**
 * Spring AI 支持通过 ToolContext API 向工具传递额外的上下文信息。此功能允许你提供用户自定义的额外数据，这些数据可与 AI 模型传递的工具参数一起在工具执行过程中使用。
 */
@Slf4j
@Service
public class SSAToolContextService {



    @Bean("cityFunctionTool")
    @Description("获取城市的详细信息,如经纬度、别称、信息更新的时间戳")
    Function<CityRequest,CityResp> cityFunctionTool() {//ToolContext toolContext
        return request -> {
            // 从工具上下文中获取信息
//            Map<String, Object> contextMap = toolContext.getContext();
//            String date = (String) contextMap.getOrDefault("date", "1999-01-01");
//
//            log.info("调用工具上下文获取信息:{}",date);
            // 构造响应
            return new CityResp("北纬 30 度，东经 70 度","历史名城","date");
        };

    }

    @JsonClassDescription("城市信息查询入参")
    public record CityRequest(
            @JsonProperty(required = true)
            @JsonPropertyDescription("城市名称,例如:北京、上海")
            String cityName
    ) {}

    /**
     * 返回类型必须可被 Jackson 序列化,结果会以 JSON 形式回传给模型
     */
    @JsonClassDescription("城市信息查询出参")
    public record CityResp(
            @JsonPropertyDescription("城市经纬度,例如:北纬 30 度，东经 120 度")
            String cityLngLat,
            @JsonPropertyDescription("城市别称,例如:洛阳的别称`牡丹花城`")
            String cityNickName,
            @JsonPropertyDescription("信息生成的时间戳,例如:截止到20260101生成的信息")
            String timeStamp
    ) {
    }
}
