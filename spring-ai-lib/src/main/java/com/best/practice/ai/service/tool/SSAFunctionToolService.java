package com.best.practice.ai.service.tool;

import com.fasterxml.jackson.annotation.JsonClassDescription;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.model.ToolContext;
import org.springframework.ai.tool.function.FunctionToolCallback;
import org.springframework.stereotype.Service;

import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * Spring AI 工具的两种定义方式:
 * 1) 方法即工具(@Tool 声明式) ,(ToolCallback编程式) —— —— 见 {@link SSAMethodToolService}
 * 2) 函数即工具(Function 编程式)—— 即本类(@Bean 声明式)、(FunctionTollCallback编程式)
 *
 * > Spring AI 内置支持通过函数定义工具，既可通过底层的 FunctionToolCallback 实现以编程方式配置，也能作为运行时解析的 @Bean 动态注册。
 */
@Slf4j
@Service
public class SSAFunctionToolService implements BiFunction<SSAFunctionToolService.WeatherRequest, ToolContext, SSAFunctionToolService.WeatherResponse> {

    @Override
    public WeatherResponse apply(WeatherRequest request, ToolContext toolContext) {
        log.info("[Function Tool] 触发天气查询, city={}", request.city());
        String date = (String)toolContext.getContext().getOrDefault("date", "none");
        return new WeatherResponse(request.city() +date+ " 26℃, 晴");
    }

    /**
     * 入参类型: 字段上的 @JsonPropertyDescription 会进入 JSON Schema,
     * 模型据此理解参数含义。@JsonClassDescription 是对入参整体的描述。
     */
    @JsonClassDescription("天气查询入参")
    public record WeatherRequest(
            @JsonProperty(required = true)
            @JsonPropertyDescription("城市名称,例如:北京、上海")
            String city
    ) {}

    /**
     * 返回类型必须可被 Jackson 序列化,结果会以 JSON 形式回传给模型
     */
    public record WeatherResponse(String weather) {}


    /** 编程式声明函数即工具
     * > FunctionToolCallback.Builder 允许你构建 FunctionToolCallback 实例并提供以下关键工具信息：
     *  - name：工具名称。AI 模型通过此名称识别调用工具，因此同一上下文中不允许存在同名工具。对于特定聊天请求，模型可用的所有工具名称必须保持全局唯一。（必需项）
     *  - toolFunction：表示工具方法的函数式对象（Function、Supplier、Consumer 或 BiFunction）。（必需项）Java（编程语言）
     *  - description：工具描述，用于帮助模型判断何时及如何调用该工具。若未提供，将使用方法名称作为工具描述。但强烈建议提供详细描述，这对模型理解工具用途及使用方法至关重要。若描述不充分，可能导致模型在该调用工具时未调用，或错误调用工具。
     *  - inputType：函数输入类型。（必需项）
     *  - inputSchema：工具输入参数的 JSON Schema。若未提供，将基于 inputType 自动生成 Schema。你可使用 @ToolParam 注解提供输入参数的额外信息（如描述、是否必需等），默认情况下所有输入参数均为必需参数。详见 JSON Schema 章节说明。
     *  - toolMetadata：定义额外设置的 ToolMetadata 实例（如是否将结果直接返回客户端、使用的结果转换器等），可通过 ToolMetadata.Builder 类构建。
     *  - toolCallResultConverter：用于将工具调用结果转换为 String 对象并返回 AI 模型的 ToolCallResultConverter 实例（未配置时默认使用 DefaultToolCallResultConverter）。
     * >ToolMetadata.Builder 允许你构建 ToolMetadata 实例并为工具定义以下附加设置：
     *  - returnDirect：控制工具结果直接返回客户端（true）还是传回模型（false）。详见 [_return_direct] 章节说明。
     * @return FunctionToolCallback 函数即工具范式的回调
     */

    public FunctionToolCallback getWeatherFunctionTool(){
        return FunctionToolCallback
                .builder("currentWeather", new SSAFunctionToolService())
                .description("Get the weather in location")
                .inputType(WeatherRequest.class)
                .build();
    }


    /**
     * > 可将工具定义为 Spring Bean，由 Spring AI 通过 ToolCallbackResolver 接口（具体实现为 SpringBeanToolCallbackResolver）在运行时动态解析
     * - Bean 名称将作为工具名称，可通过 Spring Framework 的 @Description 注解提供工具描述，若未提供描述，则使用方法名称作为工具描述
     @Configuration(proxyBeanMethods = false)
     class WeatherTools {

     WeatherService weatherService = new WeatherService();

     @Bean
     @Description("Get the weather in location")
     Function<WeatherRequest, WeatherResponse> currentWeather() {
     return weatherService;
     }

     }
     */
}