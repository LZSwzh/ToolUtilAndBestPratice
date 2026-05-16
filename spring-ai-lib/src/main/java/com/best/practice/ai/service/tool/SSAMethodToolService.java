package com.best.practice.ai.service.tool;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.model.ToolContext;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.definition.ToolDefinition;
import org.springframework.ai.tool.method.MethodToolCallback;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.Map;

/**
 * Spring AI tool定义得声明式定义
 * 该方法既可以是静态方法也可以是实例方法，并且可具有任意可见性（public、protected、package-private 或 private）。
 * 包含该方法的类既可以是顶级类也可以是嵌套类，同样支持任意可见性（只要在计划实例化的位置可访问即可）。
 * tips1:
 * > Spring AI 为使用 @Tool 注解的方法提供内置的 AOT（提前编译）支持，前提是包含这些方法的类必须是 Spring Bean
 * > 否则通过使用 @RegisterReflection(memberCategories = MemberCategory.INVOKE_DECLARED_METHODS) 注解标记该类。
 * tips2:
 * > 需要模型底层支持工具调用
 * tips3:
 * > 以下类型目前不支持作为工具方法的参数或返回类型：
 * - Optional，异步类型（如 CompletableFuture、Future），响应式类型 （如 Flow、Mono、Flux），函数式类型（如 Function、Supplier、Consumer）
 * - 函数式类型在基于函数的工具规范方法中受支持
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class SSAMethodToolService {
    /**
     * name           : 不写默认和方法名一致
     * description    : 编写工具得描述，强烈建议编写，因为这个是路由哪个tool得判断依据
     * returnDirect   : 控制工具结果直接返回客户端（true）还是传回模型（false）;默认false直接返回模型；开启后可以将方法的输出直接给程序
     * resultConverter: 工具调用结果转换为字符串对象的 ToolCallResultConverter 实现
     *
     * @return
     * > 若方法有返回值，则返回类型必须是可序列化类型，因为结果将被序列化并发送回模型
     * 你还可使用 Swagger 的 @Schema 或 Jackson 的 @JsonProperty 注解。详见 JSON Schema 章节说明
     *
     * > @ToolParam 注解允许你配置工具参数的关键信息：
     * -  description：参数描述，用于帮助模型更准确地理解如何使用该参数。例如：参数格式要求、允许取值范围等。
     * -  required：指定参数是否为必需项（默认值：true，即所有参数默认必需）。
     * > 标注了 @Nullable 注解，则该参数将被视为可选参数，除非通过 @ToolParam 显式标记为必需参数
     */
    @Tool(name="weatherTool",description = "查询今天得天气",returnDirect = true)
    public String getWeather(ToolContext toolContext) {
        Map<String, Object> contextMap = toolContext.getContext();
        log.info("触发工具调用  weatherTool............,context:{}",contextMap.getOrDefault("date","none"));
        return "26摄氏度；晴";
    }


    /**
     * > @ToolParam 注解允许你配置工具参数的以下关键信息：
     * description：参数描述，用于帮助模型更准确地理解如何使用该参数。例如：参数格式要求、允许取值范围等。
     * required：指定参数是否为必需项（默认值：true，即所有参数默认必需）。
     *
     * > 标注了 @Nullable 注解，则该参数将被视为可选参数，除非通过 @ToolParam 显式标记为必需参数
     * > 除 @ToolParam 注解外，你还可使用 Swagger 的 @Schema 或 Jackson 的 @JsonProperty 注解。详见 JSON Schema 章节说明
     * @return
     */
    public String getCurrentDateTime(ToolContext toolContext) {

        Map<String, Object> contextMap = toolContext.getContext();
        log.info("触发工具调用  getCurrentDateTime............,context:{}",contextMap.getOrDefault("date","none"));
        return LocalDateTime.now().atZone(LocaleContextHolder.getTimeZone().toZoneId()).toString();
    }

    public MethodToolCallback getCurrentTimeCbk(){
        // 反射获取方法
        Method method = ReflectionUtils.findMethod(SSAMethodToolService.class, "getCurrentDateTime");
        if (null == method) {
            throw new IllegalArgumentException("无法获取工具:getCurrentDateTime");
        }

        // 为 ToolDefinition 提供一个空的 inputSchema
        String inputSchema = "{}"; // 空的 JSON Schema 表示没有输入参数

        // 构建 ToolDefinition
        ToolDefinition toolDefinition = ToolDefinition.builder()
                .name("getCurrentDateTime")
                .description("Get the current date and time in the user's timezone")
                .inputSchema(inputSchema)
                .build();

        // 构建 MethodToolCallback
        MethodToolCallback toolCallback = MethodToolCallback.builder()
                .toolDefinition(toolDefinition)
                .toolMethod(method)
                .toolObject(this) // 传递当前实例作为 toolObject
                .build();

        return toolCallback;
    }
}
